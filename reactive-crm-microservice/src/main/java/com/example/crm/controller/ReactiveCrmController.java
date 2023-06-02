package com.example.crm.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crm.document.CustomerDocument;
import com.example.crm.service.ReactiveCrmService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
@CrossOrigin
@Validated
public class ReactiveCrmController {
	private final ReactiveCrmService crmService;

	public ReactiveCrmController(ReactiveCrmService crmService) {
		this.crmService = crmService;
	}

	@GetMapping("{identity}")
	public Mono<CustomerDocument> getCustomerByIdentity(@PathVariable String identity) {
		return crmService.getCustomerById(identity);
	}

	@GetMapping(params = { "pageNo", "pageSize" })
	public Flux<CustomerDocument> getCustomersByPage(@RequestParam int pageNo, @RequestParam int pageSize) {
		return crmService.getCustomerById(pageNo, pageSize);
	}

	@PostMapping
	public Mono<CustomerDocument> acquireCustomer(@RequestBody @Validated CustomerDocument customer) {
		return crmService.addCustomer(customer);
	}

	@PutMapping("{identity}")
	public Mono<CustomerDocument> acquireCustomer(@PathVariable String identity,
			@RequestBody @Validated CustomerDocument customer) {
		return crmService.updateCustomer(customer);

	}

	@DeleteMapping("{identity}")
	public Mono<CustomerDocument> releaseCustomer(@PathVariable String identity) {
		return crmService.removeCustomer(identity);

	}
}
