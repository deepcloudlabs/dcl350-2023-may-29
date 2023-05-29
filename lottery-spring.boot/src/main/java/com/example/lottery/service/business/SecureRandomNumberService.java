package com.example.lottery.service.business;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import com.example.lottery.service.RandomNumberService;

@Service
public class SecureRandomNumberService implements RandomNumberService {

	private SecureRandom random = new SecureRandom();

	@Override
	public int generate(int min, int max) {
		return random.nextInt(min, max + 1);
	}

}
