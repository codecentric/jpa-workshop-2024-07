package de.codecentric.workshops.jpaworkshop.jpa.datatypes;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
	Message findById(long messageId);

	List<Message> findAllBySender(User sender);

	List<Message> findAllBySenderId(long id);

	List<Message> findAllBySenderId(long id, Sort sort);

	List<Message> findAllBySenderIdOrderByTimestamp(long id);

	List<Message> findAllBySenderName(String senderName);

	List<Message> findAllBySenderIdAndContentContains(long senderId, String content);

	int countMessagesBySenderId(long senderId);

}
