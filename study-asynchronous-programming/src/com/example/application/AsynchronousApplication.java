package com.example.application;

import com.example.service.StandardService;

public class AsynchronousApplication {

	public static void main(String[] args) {
		System.err.println("AsynchronousApplication is just started!");
		var standardService = new StandardService();
		standardService.methodB()
		               .thenAcceptAsync(result->System.err.println("Result is %d".formatted(result)));
		for (var i=0L;i<2_0000_000_000_000L;++i) {
			if (i%1_000_000_000 == 0)
				System.err.println("AsynchronousApplication is processing data...%d".formatted(i));
		}
		System.err.println("AsynchronousApplication is just completed!");
		
	}

}
