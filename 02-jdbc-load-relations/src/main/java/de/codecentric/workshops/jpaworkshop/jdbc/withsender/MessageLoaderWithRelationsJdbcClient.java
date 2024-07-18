package de.codecentric.workshops.jpaworkshop.jdbc.withsender;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
public class MessageLoaderWithRelationsJdbcClient {
	private static final RowMapper<Message> MESSAGE_ROW_MAPPER = (rs, rowNum) -> new Message(
		rs.getLong("message_id"),
		new User(rs.getLong("user_id"), rs.getString("username")),
		rs.getString("receiver"),
		rs.getString("content")
	);
	private final JdbcClient jdbcClient;

	public MessageLoaderWithRelationsJdbcClient(JdbcClient jdbcClient) {
		this.jdbcClient = jdbcClient;
	}

	public Message loadMessage(long id) {
		return jdbcClient
			.sql("select * from messages join users on sender_id = user_id where message_id = ?")
			.param(id)
			.query(MESSAGE_ROW_MAPPER)
			.single();
	}

	public List<Message> loadAllMessages() {
		return jdbcClient.sql("select * from messages join users on sender_id = user_id")
			.query(MESSAGE_ROW_MAPPER)
			.list();
	}
}
