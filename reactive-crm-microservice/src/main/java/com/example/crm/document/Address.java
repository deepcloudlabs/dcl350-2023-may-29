package com.example.crm.document;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
public class Address {
	@Field("type")
	private AddressType addressType;
	private String country;
	private String city;
	private String street;
	@Field("line")
	private String addressLine;
	private String zipCode;
}
