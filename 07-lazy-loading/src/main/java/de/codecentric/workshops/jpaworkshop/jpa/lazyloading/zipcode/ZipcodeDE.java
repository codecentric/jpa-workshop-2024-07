package de.codecentric.workshops.jpaworkshop.jpa.lazyloading.zipcode;

public class ZipcodeDE extends Zipcode {
	protected ZipcodeDE(String value) {
		super(value);
		if (value.length() != 5) {
			throw new IllegalArgumentException();
		}
	}
}
