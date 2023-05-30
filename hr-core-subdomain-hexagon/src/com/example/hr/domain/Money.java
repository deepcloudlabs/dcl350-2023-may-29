package com.example.hr.domain;

import java.util.Objects;

import com.example.ddd.ValueObject;

@ValueObject
public final class Money {
	private final FiatCurrency currency;
	private final double value;

	private Money(FiatCurrency currency, double value) {
		this.currency = currency;
		this.value = value;
	}

	public FiatCurrency getCurrency() {
		return currency;
	}

	public double getValue() {
		return value;
	}

	public static Money valueOf(FiatCurrency currency, double value) {
		Objects.requireNonNull(currency);
		if (value <= 0.0)
			throw new IllegalArgumentException("Money value must be a positive value");
		return new Money(currency, value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(currency, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		return currency == other.currency && Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}

	@Override
	public String toString() {
		return "Money [currency=" + currency + ", value=" + value + "]";
	}

	public Money multiply(double factor) {
		return Money.valueOf(currency, value * factor);
	}

}
