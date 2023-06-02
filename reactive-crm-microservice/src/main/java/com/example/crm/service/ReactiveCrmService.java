package com.example.crm.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.crm.document.CustomerDocument;
import com.example.crm.repository.CustomerDocumentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveCrmService {
	private final CustomerDocumentRepository custRepo;
	
	public ReactiveCrmService(CustomerDocumentRepository custRepo) {
		this.custRepo = custRepo;
	}

	public Mono<CustomerDocument> getCustomerById(String identity) {
		return custRepo.findById(identity);
	}

	public Flux<CustomerDocument> getCustomerById(int pageNo, int pageSize) {
		return custRepo.findAll(PageRequest.of(pageNo, pageSize));
	}

	public Mono<CustomerDocument> addCustomer(CustomerDocument customer) {
		return custRepo.insert(customer);
	}

	public Mono<CustomerDocument> updateCustomer(CustomerDocument customer) {
		return custRepo.save(customer);
	}

	public Mono<CustomerDocument> removeCustomer(String identity) {
		return custRepo.findById(identity)
		               .doOnNext(custRepo::delete);
	}

}
