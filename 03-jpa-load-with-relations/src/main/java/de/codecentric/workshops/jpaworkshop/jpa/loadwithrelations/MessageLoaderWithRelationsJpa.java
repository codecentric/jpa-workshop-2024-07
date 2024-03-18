package de.codecentric.workshops.jpaworkshop.jpa.loadwithrelations;

import java.util.List;

import jakarta.persistence.EntityManager;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

@Service
public class MessageLoaderWithRelationsJpa {
	private final EntityManager entityManager;

	public MessageLoaderWithRelationsJpa(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Message loadMessage(long id) {
		throw new NotImplementedException("TODO");
	}

	public List<Message> loadAllMessages() {
		throw new NotImplementedException("TODO");
	}

	List<Message> findAllBySender(User sender) {
		throw new NotImplementedException("TODO");
	}

	List<Message> findAllBySenderId(long id) {
		throw new NotImplementedException("TODO");
	}

	public List<Message> findAllBySenderIdAndContentContains(long senderId, String content) {
		throw new NotImplementedException("TODO");
	}

	public List<Message> findAllBySenderName(String senderName) {
		throw new NotImplementedException("TODO");
	}

	public long countMessagesBySenderId(long senderId) {
		throw new NotImplementedException("TODO");
	}
}
