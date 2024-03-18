package de.codecentric.workshops.jpaworkshop.jdbc.simple;

import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageLoaderJdbcTemplate {
	private final JdbcTemplate jdbcTemplate;

	public MessageLoaderJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Message loadMessage(long id) {
		throw new NotImplementedException("TODO");
	}

	public List<Message> loadAllMessages() {
		throw new NotImplementedException("TODO");
	}
}
