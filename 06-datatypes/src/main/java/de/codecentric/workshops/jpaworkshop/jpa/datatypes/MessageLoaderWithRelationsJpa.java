package de.codecentric.workshops.jpaworkshop.jpa.datatypes;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.JoinType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
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
		return findAllBySenderIdJPQL(sender.getId());
	}

	List<Message> findAllBySenderIdJPQL(long id) {
		return entityManager.createQuery("SELECT m FROM Message m WHERE sender.id = :id", Message.class)
			.setParameter("id", id)
			.getResultList();
	}

	public List<Message> findAllBySenderIdAndContentContains(long senderId, String content) {
		return entityManager.createQuery("SELECT m FROM Message m where sender.id = :id and m.content like :content",
				Message.class
			)
			.setParameter("id", senderId)
			.setParameter("content", "%" + content + "%")
			.getResultList();
	}

	public List<Message> findAllBySenderName(String senderName) {
		return entityManager.createQuery("SELECT m FROM Message m where sender.name = :name", Message.class)
			.setParameter("name", senderName)
			.getResultList();
	}

	public long countMessagesBySenderId(long senderId) {
		return entityManager.createQuery("SELECT count(m) from Message m where sender.id = :id", long.class)
			.setParameter("id", senderId)
			.getSingleResult();
	}

	@Transactional
	public Message save(Message newMessage) {
		entityManager.persist(newMessage);
		return newMessage;
	}

	public List<Message> findAllBySenderIdOrderByTimestamp(Long senderId) {
		return entityManager.createQuery("""
			                  SELECT m FROM Message m 
			                  WHERE sender.id = :id
							  ORDER BY m.timestamp
			""", Message.class).setParameter("id", senderId).getResultList();
	}

	public List<Message> findAllBySenderIdJPQL(Long senderId, Sort sort) {
		final var queryBuilder = new StringBuilder("SELECT m FROM Message m WHERE sender.id = :id ");
		if (sort.isSorted()) {
			queryBuilder.append(" ORDER BY ");
			var needComma = false;
			for (Order order : sort) {
				if (needComma) {
					queryBuilder.append(", ");
				}
				// need to make sure "property" is safe and not user-controlled
				queryBuilder.append(order.getProperty()).append(" ").append(order.getDirection()).append(" ");
				needComma = true;
			}
		}
		return entityManager.createQuery(queryBuilder.toString(), Message.class)
			.setParameter("id", senderId)
			.getResultList();
	}

	public List<Message> findAllBySenderIdCriteria(Long senderId, Sort sort) {
		final var cb = entityManager.getCriteriaBuilder();
		final var cq = cb.createQuery(Message.class);

		var m = cq.from(Message.class);
		var sender = m.join("sender", JoinType.LEFT);

		cq.select(m).where(cb.equal(sender.get("id"), senderId));

		if (sort.isSorted()) {
			cq.orderBy(sort.stream()
				.map(order -> order.isAscending()
							  ? cb.asc(m.get(order.getProperty()))
							  : cb.desc(m.get(order.getProperty())))
				.toList());
		}

		return entityManager.createQuery(cq).getResultList();
	}
}
