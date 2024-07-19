package de.codecentric.workshops.jpaworkshop.jpa.controllingnames;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name= "\"user\"")
public class User {
	@EmbeddedId
	private UserPK pk;
//	@Id
//	@Column(name = "user_id")
////	@GeneratedValue(generator = "myGenerator")
//	private Long id;
//	@Id
//	private String name;
	@Enumerated(EnumType.STRING)
	private UserLevel level;

	public User() {
	}

	public User(UserPK key, UserLevel level) {
		this.pk = key;
//		this.name = name;
		this.level = level;
	}

	public String getName() {
		return this.pk.getName();
	}


	public UserLevel getLevel() {
		return level;
	}

	public void setLevel(UserLevel level) {
		this.level = level;
	}
}
