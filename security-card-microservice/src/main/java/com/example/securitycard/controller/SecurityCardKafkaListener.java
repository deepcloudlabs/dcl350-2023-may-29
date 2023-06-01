package com.example.securitycard.controller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SecurityCardKafkaListener {

	@KafkaListener(topics = "${hrEventTopic}")
	public void listenTopic(String event) {
		System.out.println("New event has arrived: %s".formatted(event));
	}
}
