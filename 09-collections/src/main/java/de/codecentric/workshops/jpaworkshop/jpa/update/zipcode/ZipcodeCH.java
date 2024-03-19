package de.codecentric.workshops.jpaworkshop.jpa.update.zipcode;

public class ZipcodeCH extends Zipcode {
	protected ZipcodeCH(String value) {
		super(value);
		if (value.length() != 4) {
			throw new IllegalArgumentException();
		}
	}
}
