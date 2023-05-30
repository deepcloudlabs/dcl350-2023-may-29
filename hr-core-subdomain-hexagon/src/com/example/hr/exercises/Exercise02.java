package com.example.hr.exercises;

import com.example.hr.domain.JobStyle;

@SuppressWarnings("unused")
public class Exercise02 {

	public static void main(String[] args) {
		var ft = JobStyle.valueOf("FULL_TIME");
		for (var js : JobStyle.values()) {
			System.out.println("%s: %d,%d".formatted(js.name(), js.ordinal(), js.getSgkId()));
		}

	}

}
