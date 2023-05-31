package com.example.hr.application.business.event;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import com.example.ddd.DomainEvent;
import com.example.hr.domain.TcKimlikNo;

@DomainEvent
public abstract class EmployeeEvent {
	private final String eventId = UUID.randomUUID().toString();
	private final long timestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
	private final TcKimlikNo identityNo;

	public EmployeeEvent(TcKimlikNo identityNo) {
		this.identityNo = identityNo;
	}

	public String getEventId() {
		return eventId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public TcKimlikNo getIdentityNo() {
		return identityNo;
	}

}
