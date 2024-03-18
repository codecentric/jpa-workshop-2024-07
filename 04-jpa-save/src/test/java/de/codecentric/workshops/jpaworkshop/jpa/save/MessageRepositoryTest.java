package de.codecentric.workshops.jpaworkshop.jpa.save;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

@SpringBootTest
class MessageRepositoryTest {
	@Autowired
	JdbcClient jdbcClient;
	@Autowired
	EntityManagerFactory emf;
	@Autowired
	MessageRepository underTest;
	final User user1 = new User("user1", UserLevel.USER);
	final User user2 = new User("user2", UserLevel.MODERATOR);
	private Message msg1;
	private Message msg2;
	private Message msg3;

	@BeforeEach
	void resetDb() {
		final EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.createQuery("DELETE from Message").executeUpdate();
		entityManager.createQuery("DELETE from User").executeUpdate();
		entityManager.persist(user1);
		entityManager.persist(user2);
		msg1 = new Message(user1, "to1", "content one");
		msg1.setId(1001L);
		entityManager.persist(msg1);
		msg2 = new Message(user2, "to2", "content two");
		msg2.setId(1002L);
		entityManager.persist(msg2);
		msg3 = new Message(user1, "to3", "content three");
		msg3.setId(1003L);
		entityManager.persist(msg3);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Test
	void loadsSingleMessage() {
		final Optional<Message> loadedMessageOption = underTest.findById(msg1.getId());
		Assertions.assertThat(loadedMessageOption).isPresent();
		final Message loadedMessage = loadedMessageOption.get();
		assertThat(loadedMessage.getId()).isEqualTo(msg1.getId());
		assertThat(loadedMessage.getContent()).isEqualTo("content one");
	}

	@Test
	void loadsAllMessages() {
		final List<Message> messages = underTest.findAll();
		Assertions.assertThat(messages).hasSize(3);
		assertThat(messages.get(0).getId()).isEqualTo(msg1.getId());
		assertThat(messages.get(1).getId()).isEqualTo(msg2.getId());
		assertThat(messages.get(2).getId()).isEqualTo(msg3.getId());
	}

	@Test
	void findsMessageBySender() {
		final List<Message> actual = underTest.findAllBySender(user1);
		Assertions.assertThat(actual).hasSize(2);
		assertThat(actual.get(0).getId()).isEqualTo(msg1.getId());
		assertThat(actual.get(1).getId()).isEqualTo(msg3.getId());
	}

	@Test
	void findsMessageBySenderId() {
		final List<Message> actual = underTest.findAllBySenderId(user1.getId());
		Assertions.assertThat(actual).hasSize(2);
		assertThat(actual.get(0).getId()).isEqualTo(msg1.getId());
		assertThat(actual.get(1).getId()).isEqualTo(msg3.getId());
	}

	@Test
	void findsBySenderName() {
		final List<Message> actual = underTest.findAllBySenderName("user1");
		Assertions.assertThat(actual).hasSize(2);
		assertThat(actual.get(0).getId()).isEqualTo(msg1.getId());
		assertThat(actual.get(1).getId()).isEqualTo(msg3.getId());
	}

	@Test
	void findsBySenderIdAndContent() {
		var msg4 = new Message(user1, "to4", "another three");
		msg4.setId(1004L);
		msg4 = underTest.save(msg4);
		final List<Message> actual = underTest.findAllBySenderIdAndContentContains(user1.getId(), "three");
		Assertions.assertThat(actual).hasSize(2);
		assertThat(actual.get(0).getId()).isEqualTo(msg3.getId());
		assertThat(actual.get(1).getId()).isEqualTo(msg4.getId());
	}

	@Test
	void findsCountBySenderId() {
		final int actual = underTest.countMessagesBySenderId(user1.getId());
		assertThat(actual).isEqualTo(2);
	}

	@Test
	void savesMessage() {
		final Message newMessage = new Message(user1, "to1", "content_new");
		newMessage.setId(1005L);
		final Message savedMessage = underTest.save(newMessage);
		assertThat(savedMessage).usingRecursiveComparison().isEqualTo(newMessage);
		assertThat(jdbcClient.sql("SELECT count(*) from messages;").query(int.class).single()).isEqualTo(4);
		assertThat(jdbcClient.sql("SELECT count(*) from messages where ID=?;")
			.param(1, savedMessage.getId())
			.query(int.class)
			.single()).isEqualTo(1);
		jdbcClient.sql("SELECT * from messages where ID=?;").param(1, 0).query(rs -> {
			assertThat(rs.getString("content")).isEqualTo("content_new");
		});
	}
}