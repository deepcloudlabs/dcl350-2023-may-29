package com.example.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class StandardService {
	public int methodA() {
		try {TimeUnit.SECONDS.sleep(5);}catch (Exception e) {}
		return 42;
	}
	public CompletableFuture<Integer> methodB() {
		return CompletableFuture.supplyAsync(()->{
			try {TimeUnit.SECONDS.sleep(3);}catch (Exception e) {}
			return 42;			
		});
	}
}
