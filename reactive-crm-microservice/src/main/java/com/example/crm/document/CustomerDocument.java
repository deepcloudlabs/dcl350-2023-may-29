package com.example.crm.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection="customers")
@Data
public class CustomerDocument {
	@Id
	private String identity;
	@Field("name")
	private String fullname;
	private String email;
	private String sms;
	@Field("byear")
	private int birthYear;
	private List<Address> addresses;
}
