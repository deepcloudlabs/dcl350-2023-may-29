package com.example.hr.domain;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.example.ddd.ValueObject;

// Value Object: i) Has no identity -> Entity 
//              ii) Immutable Class
@ValueObject
public final class TcKimlikNo {
	private static Map<String,TcKimlikNo> CACHE = new ConcurrentHashMap<>();
	private final String value;

	private TcKimlikNo(String value) {
		this.value = value;
	}

	// factory method
	public static TcKimlikNo valueOf(String value) {
		// validation
		if (!isValid(value)) 
			throw new IllegalArgumentException("This is not a valid identity no.");
		// object pooling
		var identity = CACHE.get(value);
		if (Objects.isNull(identity)) {
			identity = new TcKimlikNo(value);
			CACHE.put(value, identity);
		}
		return identity;
	}
	
	private static boolean isValid(String value) {
		return true;
	}

	public String getValue() {
		return value;
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
		TcKimlikNo other = (TcKimlikNo) obj;
		return Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return "TcKimlikNo [value=" + value + "]";
	}

}