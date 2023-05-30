package com.example.hr.domain;

import java.util.Objects;

import com.example.ddd.ValueObject;

@ValueObject
public final class Iban {
	private final String value;

	private Iban(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static Iban of(String value) {
		if (!isValid(value))
			throw new IllegalArgumentException("This is not a valid iban.");
		return new Iban(value);
	}

	private static boolean isValid(String value) {
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Iban other = (Iban) obj;
		return Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return "Iban [value=" + value + "]";
	}
	
}