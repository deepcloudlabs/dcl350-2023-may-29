package com.example.hr.domain;

import java.util.Objects;

import com.example.ddd.ValueObject;

@ValueObject
public final class Rate {
	public static final Rate PROMOTION_RATE = Rate.createRate(25);
	private final int value;

	private Rate(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static Rate createRate(int value) {
		if (value <= 0)
			throw new IllegalArgumentException("Rate must be positive!");
		return new Rate(value);
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
		Rate other = (Rate) obj;
		return value == other.value;
	}

	@Override
	public String toString() {
		return "Rate [value=" + value + "]";
	}

	public double getNormalizedValue() {
		return this.value/100.0;
	}

}
