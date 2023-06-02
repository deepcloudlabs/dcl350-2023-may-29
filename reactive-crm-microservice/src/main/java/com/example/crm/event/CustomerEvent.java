package com.example.crm.event;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
   use = JsonTypeInfo.Id.NAME,
   property = "eventType"
)
@JsonSubTypes({
	@Type(value=AddressChangedEvent.class, name = "addressChangedEvent")
})
public abstract class CustomerEvent {
	private final String eventId = UUID.randomUUID().toString();
	private final long timestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
	private final String identity;
	
	public CustomerEvent(String identity) {
		this.identity = identity;
	}

	public String getEventId() {
		return eventId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public String getIdentity() {
		return identity;
	}



}
