package com.example.lottery.service.business;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.example.lottery.service.RandomNumberService;

@Service
@ConditionalOnProperty(name = "randomService", havingValue = "FAST")
public class FastRandomNumberService implements RandomNumberService {

	public FastRandomNumberService() {
		System.err.println("Creating FastRandomNumberService...");
	}

	@Override
	public int generate(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

}
