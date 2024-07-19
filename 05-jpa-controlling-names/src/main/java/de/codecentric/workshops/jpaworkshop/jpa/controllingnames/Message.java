package de.codecentric.workshops.jpaworkshop.jpa.controllingnames;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

@Entity
@Table(name = "messages")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne()
	@JoinColumn(name = "sender_id", referencedColumnName = "id")
	@JoinColumn(name = "sender_name", referencedColumnName = "name")
	private User sender;
	private String receiver;
	private String content;

	public Message() {
	}

	public Message(User sender, String receiver, String content) {
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
