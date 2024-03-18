package de.codecentric.workshops.jpaworkshop.jdbc.simple;

public class Message {
	private long id;
	private String sender;
	private String receiver;
	private String content;

	public Message(long message_id, String sender, String receiver, String content) {
		this.id = message_id;
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
	}

	public Message() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setMessage_Id(long id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
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
