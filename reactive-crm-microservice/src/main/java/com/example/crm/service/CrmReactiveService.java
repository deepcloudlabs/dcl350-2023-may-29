package com.example.crm.service;

import org.springframework.stereotype.Service;

import com.example.crm.document.CustomerDocument;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CrmReactiveService {

	public Mono<CustomerDocument> findCustomerByIdentity(String identity) {
		// TODO Auto-generated method stub
		return null;
	}

	public Flux<CustomerDocument> findCustomers(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public Mono<CustomerDocument> addCustomer(CustomerDocument customer) {
		// TODO Auto-generated method stub
		return null;
	}

	public Mono<CustomerDocument> updateCustomer(String identity, CustomerDocument customer) {
		// TODO Auto-generated method stub
		return null;
	}

	public Mono<CustomerDocument> removeCustomer(String identity) {
		// TODO Auto-generated method stub
		return null;
	}

}
