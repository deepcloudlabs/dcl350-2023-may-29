package com.example.lottery.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@Service
public class StandardService {
	public int methodA() {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (Exception e) {
		}
		return 42;
	}

	@TimeLimiter(name="lotteryTimeLimiter", fallbackMethod = "methodBFallback")
	public CompletableFuture<Integer> methodB() {
		var duration = ThreadLocalRandom.current().nextInt(3);
		System.err.println("Duration in sec.: %d".formatted(duration));
		return CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(duration);
			} catch (Exception e) {
			}
			return 42;
		});
	}
	public CompletableFuture<Integer> methodBFallback(Throwable t) {
		return CompletableFuture.supplyAsync(() -> {
			return 108;
		});
	}
}
