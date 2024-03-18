package de.codecentric.workshops.jpaworkshop.jpa.loadwithrelations;

public class User {
	private long id;
	private String name;
	private UserLevel level;

	public User() {
	}

	public User(long id, String name, UserLevel level) {
		this.id = id;
		this.name = name;
		this.level = level;
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

	public UserLevel getLevel() {
		return level;
	}

	public void setLevel(UserLevel level) {
		this.level = level;
	}
}
