package com.example.lottery.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "clientSideLB", havingValue = "feign")
public class LotteryFeignConsumerService {
	private final LotteryService lotteryService;
	
	public LotteryFeignConsumerService(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
		System.out.println(this.lotteryService.getClass().getName());
	}

	@Scheduled(fixedRate = 3_000)
	public void callLotteryService() {
		var lotteryNumbers = lotteryService.drawLotteryNumbers(5);
		System.out.println(lotteryNumbers);
	}
}
