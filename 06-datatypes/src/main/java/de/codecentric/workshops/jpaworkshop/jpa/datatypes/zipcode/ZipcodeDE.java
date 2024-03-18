package de.codecentric.workshops.jpaworkshop.jpa.datatypes.zipcode;

public class ZipcodeDE extends Zipcode {
	protected ZipcodeDE(String value) {
		super(value);
		if (value.length() != 5) {
			throw new IllegalArgumentException();
		}
	}
}
