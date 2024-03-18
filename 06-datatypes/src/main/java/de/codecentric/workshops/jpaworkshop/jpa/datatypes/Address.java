package de.codecentric.workshops.jpaworkshop.jpa.datatypes;

import de.codecentric.workshops.jpaworkshop.jpa.datatypes.zipcode.Zipcode;
import jakarta.persistence.Embeddable;

@Embeddable
public record Address(String street, String city, Zipcode zip) {
	// could be either javabean or record, both works as embeddable
}
