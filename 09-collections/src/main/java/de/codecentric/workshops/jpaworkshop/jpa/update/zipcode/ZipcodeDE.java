package de.codecentric.workshops.jpaworkshop.jpa.update.zipcode;

public class ZipcodeDE extends Zipcode {
	protected ZipcodeDE(String value) {
		super(value);
		if (value.length() != 5) {
			throw new IllegalArgumentException();
		}
	}
}
