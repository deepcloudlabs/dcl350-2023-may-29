package com.example.lottery.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Service
@ConditionalOnProperty(name = "clientSideLB", havingValue = "custom")
public class LotteryConsumerService {
	private final String lotteryServiceUrl = "http://%s:%d/lottery/api/v1/numbers?column=5";
	private final DiscoveryClient discoveryClient;
	private List<ServiceInstance> lotteryServiceInstances;
	private AtomicInteger nextIndex = new AtomicInteger();

	public LotteryConsumerService(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}

	@PostConstruct
	@Scheduled(fixedRate = 60_000)
	public void init() {
		this.lotteryServiceInstances = discoveryClient.getInstances("lottery");
	}

	//@Scheduled(fixedRate = 3_000)
	public void callLotteryService() {
		var restTemplate = new RestTemplate();
		// LB Strategy: Round-robin
		var size = this.lotteryServiceInstances.size();
		if (size == 0) throw new RuntimeException("No service instance is available.");
		var instance = this.lotteryServiceInstances.get(nextIndex.getAndIncrement() % size);
		var host = instance.getHost();
		var port = instance.getPort();
		var lotteryInstanceServiceUrl = lotteryServiceUrl.formatted(host, port);
		try {
			var response = restTemplate.getForEntity(lotteryInstanceServiceUrl, String.class).getBody();
			System.out.println("Response from %s: %s".formatted(lotteryInstanceServiceUrl, response));
		} catch (Exception e) {
			init();
		}
	}

	@Retry(name = "lotteryRetry", fallbackMethod = "fallbackDraw")
	@RateLimiter(name = "lotteryRateLimiter", fallbackMethod = "fallbackDraw")
	@CircuitBreaker(name = "lotteryCircuitBreaker")
	public String draw() {
		var restTemplate = new RestTemplate();
		// LB Strategy: Round-robin
		var size = this.lotteryServiceInstances.size();
		if (size == 0) throw new RuntimeException("No service instance is available.");
		var instance = this.lotteryServiceInstances.get(nextIndex.getAndIncrement() % size);
		var host = instance.getHost();
		var port = instance.getPort();
		var lotteryInstanceServiceUrl = lotteryServiceUrl.formatted(host, port);
		var response = restTemplate.getForEntity(lotteryInstanceServiceUrl, String.class).getBody();
		System.out.println("Response from %s: %s".formatted(lotteryInstanceServiceUrl, response));
		return response;
	}
	
	public String fallbackDraw(Exception e) {
		return "[[22,45],[32,85],[16,41],[55,71],[66,72]]";
	}
}
