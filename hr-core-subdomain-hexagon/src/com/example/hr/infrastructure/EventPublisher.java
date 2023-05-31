package com.example.hr.infrastructure;

import static com.example.hexagonal.PortType.DRIVEN_PORT;

import com.example.hexagonal.Port;

@Port(type=DRIVEN_PORT)
public interface EventPublisher<E> {
	public void publish(E event);
}
