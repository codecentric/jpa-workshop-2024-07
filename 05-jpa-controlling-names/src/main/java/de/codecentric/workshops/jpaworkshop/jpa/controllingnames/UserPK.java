package de.codecentric.workshops.jpaworkshop.jpa.controllingnames;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserPK {
	private long id;
	private String name;

	public UserPK() {
	}

	public UserPK(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
