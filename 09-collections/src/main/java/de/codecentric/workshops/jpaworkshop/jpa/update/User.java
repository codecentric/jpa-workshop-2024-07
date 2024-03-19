package de.codecentric.workshops.jpaworkshop.jpa.update;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"user\"")
public class User {
	@Id
	@GeneratedValue(generator = "myGenerator")
	@Column(name = "user_id")
	private Long id;
	private String name;
	@Enumerated(EnumType.STRING)
	private UserLevel level;
	private LocalDate dateOfBirth;
	@Embedded
	private Address address;
	@OneToMany(mappedBy = "sender")
	@OrderBy("timestamp desc")
	private Set<Message> sentMessages = new HashSet<>();

	@ElementCollection
	@OrderColumn
	@CollectionTable(name = "wishlist")
	private List<WishlistItem> wishlist = new ArrayList<>();


	public User() {
	}

	public User(String name, UserLevel level, LocalDate dateOfBirth) {
		this.name = name;
		this.level = level;
		this.dateOfBirth = dateOfBirth;
	}

	public Set<Message> getSentMessages() {
		return sentMessages;
	}

	public void setSentMessages(Set<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<WishlistItem> getWishlist() {
		return wishlist;
	}

	public void setWishlist(List<WishlistItem> wishlist) {
		this.wishlist = wishlist;
	}
}
