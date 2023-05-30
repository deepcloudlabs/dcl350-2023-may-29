package com.example.hr.domain;

import com.example.ddd.ValueObject;

@ValueObject
public enum JobStyle {
	FULL_TIME(100), PART_TIME(200), INTERN(300);
	private final int sgkId;

	private JobStyle(int sgkId) {
		this.sgkId = sgkId;
	}

	public int getSgkId() {
		return sgkId;
	}
	
}
