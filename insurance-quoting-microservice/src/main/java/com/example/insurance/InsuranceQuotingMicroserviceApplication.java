package com.example.insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class InsuranceQuotingMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsuranceQuotingMicroserviceApplication.class, args);
	}

	@KafkaListener(topics="crm-events")
	public void readEvents(String event) {
		System.err.println(event);
	}
}
