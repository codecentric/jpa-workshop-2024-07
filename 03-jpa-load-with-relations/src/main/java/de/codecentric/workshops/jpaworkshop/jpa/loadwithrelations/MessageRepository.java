package de.codecentric.workshops.jpaworkshop.jpa.loadwithrelations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
	List<Message> findAllBySenderId(long senderId);

	List<Message> findAllBySenderName(String name);

	List<Message> findAllBySenderIdAndContentContainsIgnoreCase(long senderId, String content);

	long countBySenderId(long id);

	@Query("SELECT m FROM Message m WHERE m.content like %?1%")
	List<Message> findWithContentSearch(String content);

	List<Message> findAllBySender(User sender);
}
