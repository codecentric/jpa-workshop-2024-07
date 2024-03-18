package de.codecentric.workshops.jpaworkshop.jpa.update;

import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<Message, Long> {
	@Transactional(TxType.REQUIRED)
	@Query("SELECT m FROM Message m WHERE m.id = :messageId")
	Message findByIdWithTransaction(long messageId);

	List<Message> findAllBySender(User sender);

	List<Message> findAllBySenderId(long id);

	List<Message> findAllBySenderId(long id, Sort sort);

	List<Message> findAllBySenderIdOrderByTimestamp(long id);

	List<Message> findAllBySenderName(String senderName);

	List<Message> findAllBySenderIdAndContentContains(long senderId, String content);

	int countMessagesBySenderId(long senderId);

	@Transactional(TxType.REQUIRED)
	@Nonnull Message saveAndFlush(Message m);
}
