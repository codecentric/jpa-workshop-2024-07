package de.codecentric.workshops.jpaworkshop.jpa.datatypes.zipcode;

import java.util.Objects;

public abstract class Zipcode {
	private final String value;

	protected Zipcode(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Zipcode zipcode = (Zipcode) o;
		return Objects.equals(value, zipcode.value);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(value);
	}

	public static Zipcode of(String value) {
		if (value.length() == 5) {
			return new ZipcodeDE(value);
		} else if (value.length() == 4) {
			return new ZipcodeCH(value);
		} else {
			throw new IllegalArgumentException();
		}
	}
}