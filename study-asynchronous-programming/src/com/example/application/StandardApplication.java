package com.example.application;

import com.example.service.StandardService;

public class StandardApplication {

	public static void main(String[] args) {
		System.err.println("Application is just started!");
		var standardService = new StandardService();
		var result = standardService.methodA();
		System.err.println("Result is %d".formatted(result));
		System.err.println("Application is just completed!");
	}

}
