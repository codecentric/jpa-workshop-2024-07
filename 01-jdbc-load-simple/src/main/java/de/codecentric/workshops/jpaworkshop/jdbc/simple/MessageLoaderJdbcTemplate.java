package de.codecentric.workshops.jpaworkshop.jdbc.simple;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageLoaderJdbcTemplate {
	private final JdbcTemplate jdbcTemplate;

	public MessageLoaderJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Message loadMessage(long id) {
		final List<Message> messages = jdbcTemplate.query("SELECT * FROM messages WHERE MESSAGE_ID = ?",
			(rs, rowNum) -> new Message(rs.getLong("message_id"),
				rs.getString("sender"),
				rs.getString("receiver"),
				rs.getString("content")
			),
			id
		);
		if (messages.size() != 1) {
			throw new RuntimeException("should find exactly one!");
		}
		return messages.get(0);
	}

	public List<Message> loadAllMessages() {
		return jdbcTemplate.query("SELECT * FROM messages",
			(rs, rowNum) -> new Message(rs.getLong("message_id"),
				rs.getString("sender"),
				rs.getString("receiver"),
				rs.getString("content")
			)
		);
	}
}
