package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.dto.Customer;

@Service
public class CrmReactiveWebClientService {
	private final WebClient webClient = WebClient.builder()
			                                     .baseUrl("http://localhost:6100/customers")
			                                     .build();   
	//@Scheduled(fixedRate=1)
	public void callFindAll() {
		webClient.get()
		         .uri(
		             urlBuilder -> urlBuilder.queryParam("pageNo", 0)
		                                     .queryParam("pageSize", 10)
		                                     .build()
		          ).retrieve()
		           .bodyToFlux(Customer.class)
		           .subscribe(System.out::println); 
	}
}
