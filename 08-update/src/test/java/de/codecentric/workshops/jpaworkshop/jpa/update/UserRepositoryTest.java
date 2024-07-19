package de.codecentric.workshops.jpaworkshop.jpa.update;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.util.Optional;

import de.codecentric.workshops.jpaworkshop.jpa.update.zipcode.Zipcode;
import de.codecentric.workshops.jpaworkshop.jpa.update.zipcode.ZipcodeCH;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.OptimisticLockException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest(properties = { "spring.jpa.hibernate.ddl-auto=create-drop" })
class UserRepositoryTest {
	@Autowired
	private UserRepository underTest;
	@Autowired
	EntityManager em;
	@Autowired
	EntityManagerFactory emf;

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
	void versioning() {
		final EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		final User user1 = new User("user1", UserLevel.ADMIN, LocalDate.now());
		em.persist(user1);
		System.out.println("initial version: " + user1.getVersion());
		em.getTransaction().commit();

		em.clear();

		em.getTransaction().begin();
		final User loaded_1 = em.find(User.class, user1.getId());
		em.detach(loaded_1);
		System.out.println("loaded_1 version: " + loaded_1.getVersion());
		loaded_1.setName("new name override");
		final User loaded_2 = em.find(User.class, user1.getId());
		loaded_2.setName("new name");
		em.getTransaction().commit();
		System.out.println("loaded_2 version: " + loaded_2.getVersion());

		em.getTransaction().begin();
		assertThatThrownBy(() -> em.merge(loaded_1)).isInstanceOf(OptimisticLockException.class);
		//		em.merge(loaded_1);
		//		em.getTransaction().commit();
		//
		//		em.getTransaction().begin();
		//		final User loaded_last = em.find(User.class, user1.getId());
		//		assertThat(loaded_last.getName()).isEqualTo("new name override");
	}
}