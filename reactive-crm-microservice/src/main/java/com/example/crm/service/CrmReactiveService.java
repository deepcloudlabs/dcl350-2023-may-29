package com.example.crm.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;

import com.example.crm.document.CustomerDocument;
import com.example.crm.event.AddressChangedEvent;
import com.example.crm.repository.CustomerDocumentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CrmReactiveService {
	private final CustomerDocumentRepository customerDocumentRepository;
	private final ReactiveKafkaProducerTemplate<String, String> kafkaProducerTemplate;
	private final ObjectMapper objectMapper;

	public CrmReactiveService(CustomerDocumentRepository customerDocumentRepository, ReactiveKafkaProducerTemplate<String, String> kafkaProducerTemplate, ObjectMapper objectMapper) {
		this.customerDocumentRepository = customerDocumentRepository;
		this.kafkaProducerTemplate = kafkaProducerTemplate;
		this.objectMapper = objectMapper;
	}

	public Mono<CustomerDocument> findCustomerByIdentity(String identity) {
		return customerDocumentRepository.findById(identity);
	}

	public Flux<CustomerDocument> findCustomers(int pageNo, int pageSize) {
		return customerDocumentRepository.findAll(PageRequest.of(pageNo, pageSize));
	}

	public Mono<CustomerDocument> addCustomer(CustomerDocument customer) {
		return customerDocumentRepository.insert(customer);
	}

	public Mono<CustomerDocument> updateCustomer(String identity, CustomerDocument customer) {
		return customerDocumentRepository.findById(identity)
		                          .doOnNext(managedCustomer -> {
		                        	  managedCustomer.setEmail(customer.getEmail());
		                        	  managedCustomer.setSms(customer.getSms());
		                        	  var oldAddresses = managedCustomer.getAddresses();
		                        	  var newAddresses = customer.getAddresses();
		                        	  managedCustomer.setAddresses(customer.getAddresses());
		                        	  var addressChangedEvent = new AddressChangedEvent(identity,oldAddresses,newAddresses);
		                        	  customerDocumentRepository.save(managedCustomer).subscribe((cust)->{
		                        		  try {
											String addressChangedEventAsJson = objectMapper.writeValueAsString(addressChangedEvent);
											kafkaProducerTemplate.send("crm-events", addressChangedEventAsJson).subscribe();
										} catch (JsonProcessingException e) {
											System.err.println(e.getMessage());
										}
		                        	  });
		                          });
	}

	public Mono<CustomerDocument> removeCustomer(String identity) {
		return customerDocumentRepository.findById(identity)
				                         .doOnNext(customer -> {
				                        	 customerDocumentRepository.delete(customer).subscribe();
				                         });
	}

}
