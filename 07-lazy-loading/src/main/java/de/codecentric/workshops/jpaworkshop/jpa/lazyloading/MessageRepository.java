package de.codecentric.workshops.jpaworkshop.jpa.lazyloading;

import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<Message, Long> {
	Message findById(long messageId);

	List<Message> findAllBySender(User sender);

	List<Message> findAllBySenderId(long id);

	List<Message> findAllBySenderId(long id, Sort sort);

	List<Message> findAllBySenderIdOrderByTimestamp(long id);

	List<Message> findAllBySenderName(String senderName);

	List<Message> findAllBySenderIdAndContentContains(long senderId, String content);

	int countMessagesBySenderId(long senderId);

	@EntityGraph(attributePaths = { "sender" })
	@Query("SELECT m FROM Message m WHERE m.id = :messageId")
	@Transactional(TxType.REQUIRES_NEW)
	Message findWithEntityGraph(long messageId);
}
