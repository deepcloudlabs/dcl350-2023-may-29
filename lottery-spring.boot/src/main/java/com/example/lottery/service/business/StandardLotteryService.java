package com.example.lottery.service.business;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.example.lottery.service.LotteryService;
import com.example.lottery.service.RandomNumberService;

@Service
public class StandardLotteryService implements LotteryService {
	// dependency -> interface
	private final RandomNumberService randomNumberService;
	
	// constructor injection
	public StandardLotteryService(RandomNumberService randomNumberService) {
		this.randomNumberService = randomNumberService;
	}

	@Override
	public List<Integer> draw(int max, int size) {
		return IntStream.generate(()->randomNumberService.generate(1, max))
				        .distinct()
				        .limit(size)
				        .sorted()
				        .boxed()
				        .toList();
	}

	@Override
	public List<List<Integer>> draw(int max, int size, int column) {
		return IntStream.range(0, column)
					    .parallel()
				        .mapToObj(i -> draw(max,size))
				        .toList();
	}

}
