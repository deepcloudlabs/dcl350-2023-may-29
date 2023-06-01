package com.example.lottery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lottery.service.LotteryService;

@RestController
@RefreshScope
@RequestMapping("/numbers")
@CrossOrigin
@Validated
public class LotteryRestController {
	// dependency
	private final LotteryService lotteryService;
	@Value("${lottery.max}")
	private int lotteryMax;
	@Value("${lottery.size}")
	private int lotterySize;
	
	// dependency injection through constructor
	public LotteryRestController(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}

	@GetMapping(params = {"column"})
	public List<List<Integer>> getLotteryNumbers(@RequestParam int column){
		return lotteryService.draw(lotteryMax, lotterySize, column);
	}
}
