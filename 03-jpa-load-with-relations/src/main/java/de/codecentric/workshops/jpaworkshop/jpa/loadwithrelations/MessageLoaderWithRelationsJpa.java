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
		return entityManager.find(Message.class, id);
	}

	public List<Message> loadAllMessages() {
		return entityManager.createQuery("SELECT m FROM Message m", Message.class).getResultList();
	}

	List<Message> findAllBySender(User sender) {
		return this.findAllBySenderId(sender.getId());
	}

	List<Message> findAllBySenderId(long id) {
		return entityManager.createQuery("select m from Message m where m.sender.id = :id", Message.class)
			.setParameter("id", id)
			.getResultList();
	}

	public List<Message> findAllBySenderIdAndContentContains(long senderId, String content) {
		return entityManager.createQuery("""
					select m from Message m
					where m.sender.id = :id
					and m.content like :content
				""", Message.class)
			.setParameter("id", senderId)
			.setParameter("content", "%" + content + "%")
			.getResultList();
	}

	public List<Message> findAllBySenderName(String senderName) {
		return entityManager.createQuery("select m from Message m where m.sender.name = :name", Message.class)
			.setParameter("name", senderName)
			.getResultList();

	}

	public long countMessagesBySenderId(long senderId) {
		return entityManager.createQuery("select count(m) from Message m where m.sender.id = :id", Long.class)
			.setParameter("id", senderId)
			.getSingleResult();
	}

	public long countMessagesBySenderIdSql(long senderId) {
		// this is native query using entitymanager
		// if you need to do a native sql query, i recommend using spring's jdbcclient instead
		return (long) entityManager
			.createNativeQuery("select count(*) from messages where sender_id = ?")
			.setParameter(1, senderId)
			.getSingleResult();
	}
}
