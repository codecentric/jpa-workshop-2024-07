package de.codecentric.workshops.jpaworkshop.jpa.loadwithrelations;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

@SpringBootTest
class MessageRepositoryTest {
	@Autowired
	JdbcClient jdbcClient;
//	@Autowired
	MessageRepository underTest;

	@BeforeEach
	void resetDb() {
		jdbcClient.sql("DELETE from messages;").update();
		jdbcClient.sql("DELETE from users;").update();
		jdbcClient.sql("INSERT INTO users SET id=42, name='user1', level='USER'").update();
		jdbcClient.sql("INSERT INTO users SET id=43, name='user2', level='MODERATOR'").update();
		jdbcClient.sql("INSERT INTO messages SET id=1, sender_id=42, receiver='to1', content='content one'").update();
		jdbcClient.sql("INSERT INTO messages SET id=2, sender_id=43, receiver='to2', content='content two'").update();
		jdbcClient.sql("INSERT INTO messages SET id=3, sender_id=42, receiver='to3', content='content three'").update();
	}

	@Test
	@Disabled("TODO")
	void loadsSingleMessage() {
		final Message loadedMessage = null; // underTest.???(???);
		assertThat(loadedMessage).usingRecursiveComparison()
			.isEqualTo(new Message(1, new User(42, "user1", UserLevel.USER), "to1", "content one"));
	}

	@Test
	@Disabled("TODO")
	void loadsAllMessages() {
		final List<Message> messages = Collections.emptyList(); // underTest.???(???)
		Assertions.assertThat(messages).hasSize(3);
		assertThat(messages.get(0)).usingRecursiveComparison()
			.isEqualTo(new Message(1, new User(42, "user1", UserLevel.USER), "to1", "content one"));
		assertThat(messages.get(1)).usingRecursiveComparison()
			.isEqualTo(new Message(2, new User(43, "user2", UserLevel.MODERATOR), "to2", "content two"));
		assertThat(messages.get(2)).usingRecursiveComparison()
			.isEqualTo(new Message(3, new User(42, "user1", UserLevel.USER), "to3", "content three"));
	}

	@Test
	@Disabled("TODO")
	void findsMessageBySender() {
		final List<Message> actual = Collections.emptyList();// underTest.???(???)
		Assertions.assertThat(actual).hasSize(2);
		assertThat(actual.get(0).getId()).isEqualTo(1);
		assertThat(actual.get(1).getId()).isEqualTo(3);
	}

	@Test
	@Disabled("TODO")
	void findsMessageBySenderId() {
		final List<Message> actual = Collections.emptyList();// underTest.???(???);
		Assertions.assertThat(actual).hasSize(2);
		assertThat(actual.get(0).getId()).isEqualTo(1);
		assertThat(actual.get(1).getId()).isEqualTo(3);
	}

	@Test
	@Disabled("TODO")
	void findsBySenderName() {
		final List<Message> actual = Collections.emptyList();// underTest.???(???);
		Assertions.assertThat(actual).hasSize(2);
		assertThat(actual.get(0).getId()).isEqualTo(1);
		assertThat(actual.get(1).getId()).isEqualTo(3);
	}

	@Test
	@Disabled("TODO")
	void findsBySenderIdAndContent() {
		jdbcClient.sql("INSERT INTO messages SET id=4, sender_id=42, receiver='to4', content='another three'").update();
		final List<Message> actual = Collections.emptyList();// underTest.???(???);
		Assertions.assertThat(actual).hasSize(2);
		assertThat(actual.get(0).getId()).isEqualTo(3);
		assertThat(actual.get(1).getId()).isEqualTo(4);
	}

	@Test
	@Disabled("TODO")
	void findsCountBySenderId() {
		final int actual = 0; //underTest.???(???);
		assertThat(actual).isEqualTo(2);
	}
}