package com.example.lottery.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "lottery")
public interface LotteryService {
	@GetMapping(params="column",value = "/lottery/api/v1/numbers")
	public List<List<Integer>> drawLotteryNumbers(@RequestParam int column);
}
