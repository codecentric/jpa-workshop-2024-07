package de.codecentric.workshops.jpaworkshop.jpa.update;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

import de.codecentric.workshops.jpaworkshop.jpa.update.zipcode.Zipcode;
import de.codecentric.workshops.jpaworkshop.jpa.update.zipcode.ZipcodeCH;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest(properties = { "spring.jpa.hibernate.ddl-auto=create-drop" })
class UserRepositoryTest {
	@Autowired
	private UserRepository underTest;
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	EntityManager em;

	@Test
	void savesAndLoadsUserWithAddress() {
		final User user1 = new User("user1", UserLevel.ADMIN, LocalDate.now());
		user1.setAddress(new Address("strasse", "stadt", Zipcode.of("81671")));
		underTest.save(user1);
		em.flush();
		em.clear();
		final Optional<User> loaded = underTest.findById(user1.getId());
		Assertions.assertThat(loaded).isPresent();
		assertThat(loaded.get().getAddress()).isEqualTo(new Address("strasse", "stadt", Zipcode.of("81671")));
	}

	@Test
	void savesAndLoadsUserWithSwissAddress() {
		final User user1 = new User("user1", UserLevel.ADMIN, LocalDate.now());
		user1.setAddress(new Address("strasse", "stadt", Zipcode.of("8161")));
		underTest.save(user1);
		em.flush();
		em.clear();
		final Optional<User> loaded = underTest.findById(user1.getId());
		Assertions.assertThat(loaded).isPresent();
		assertThat(loaded.get().getAddress()).isEqualTo(new Address("strasse", "stadt", Zipcode.of("8161")));
		assertThat(loaded.get().getAddress().zip()).isInstanceOf(ZipcodeCH.class);
	}

	@Test
	@Transactional
	void loadsAllSentMessagesForUser() {
		var user1 = underTest.save(new User("user1", UserLevel.ADMIN, LocalDate.now()));
		var user2 = underTest.save(new User("user2", UserLevel.ADMIN, LocalDate.now()));
		messageRepository.save(new Message(user1, "to", "content 1", Instant.now().minusSeconds(100)));
		messageRepository.save(new Message(user1, "to", "content 2", Instant.now().minusSeconds(200)));
		messageRepository.save(new Message(user1, "to", "content 3", Instant.now().minusSeconds(300)));
		messageRepository.save(new Message(user2, "to", "content 4", Instant.now().minusSeconds(300)));
		em.flush();
		em.clear();
		final User user = underTest.findById(user1.getId()).get();
		assertThat(user.getSentMessages()).hasSize(3);
		user.getSentMessages().stream().map(Message::getTimestamp).reduce(Instant.MAX, (previous, current) -> {
			assertThat(previous).isAfterOrEqualTo(current);
			return current;
		});
	}

	@Test
	void savesAndLoadsWishlist() {
		User user1 = new User("user1", UserLevel.ADMIN, LocalDate.now());
		user1.getWishlist().add(new WishlistItem("foo", URI.create("http://example.com/1")));
		user1.getWishlist().add(new WishlistItem("bar", URI.create("http://example.com/2")));
		user1.getWishlist().add(new WishlistItem("baz", URI.create("http://example.com/3")));
		user1 = underTest.save(user1);
		em.flush();
		em.clear();
		final User loadedUser = underTest.findById(user1.getId()).get();
		assertThat(loadedUser.getWishlist()).containsExactly(
			new WishlistItem("foo", URI.create("http://example.com/1")),
			new WishlistItem("bar", URI.create("http://example.com/2")),
			new WishlistItem("baz", URI.create("http://example.com/3"))
		);
	}
}