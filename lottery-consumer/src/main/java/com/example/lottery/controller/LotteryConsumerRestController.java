package com.example.lottery.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lottery.service.LotteryConsumerService;
import com.example.lottery.service.StandardService;

@RestController
@RefreshScope
@RequestMapping("/numbers")
@CrossOrigin
@Validated
public class LotteryConsumerRestController {
	private final LotteryConsumerService lotteryConsumerService;
	private final StandardService standardService;
	
	public LotteryConsumerRestController(LotteryConsumerService lotteryConsumerService, StandardService standardService) {
		this.lotteryConsumerService = lotteryConsumerService;
		this.standardService = standardService;
	}

	@GetMapping
	public String getLotteryNumbers(){
		return lotteryConsumerService.draw();
	}
	
	@GetMapping("/single")
	public int getSingleNumber() throws InterruptedException, ExecutionException{
		var result = standardService.methodB().get();
		return result; 
	}
}
