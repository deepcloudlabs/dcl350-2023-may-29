package com.example.hr.exercises;

import com.example.hr.domain.FullName;

public class Exercise01 {

	public static void main(String[] args) {
		var jack1 = new FullName("Jack","Bauer");
		var jack2 = new FullName("Jack","Bauer");
		System.out.println(jack1.firstName());
		System.out.println(jack1.lastName());
		System.out.println(jack1.toString());
		System.out.println(jack1.equals(jack2));
	}

}
