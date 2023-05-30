package com.example.lottery.service.business;

import java.security.SecureRandom;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.example.lottery.service.RandomNumberService;

@Service
@ConditionalOnProperty(name = "randomService", havingValue = "SECURE")
public class SecureRandomNumberService implements RandomNumberService {

	private SecureRandom random = new SecureRandom();

	public SecureRandomNumberService() {
		System.err.println("Creating SecureRandomNumberService...");
	}

	@Override
	public int generate(int min, int max) {
		return random.nextInt(min, max + 1);
	}

}
