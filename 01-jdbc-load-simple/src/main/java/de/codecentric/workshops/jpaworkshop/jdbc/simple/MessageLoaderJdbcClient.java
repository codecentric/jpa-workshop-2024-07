package de.codecentric.workshops.jpaworkshop.jdbc.simple;

import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
public class MessageLoaderJdbcClient {
	private final JdbcClient jdbcClient;

	public MessageLoaderJdbcClient(JdbcClient jdbcClient) {
		this.jdbcClient = jdbcClient;
	}

	public Message loadMessage(long id) {
		return jdbcClient.sql("select * from messages where message_id = ?").param(id).query(Message.class).single();
	}

	public List<Message> loadAllMessages() {
		return jdbcClient.sql("select * from messages").query(Message.class).list();
	}
}
