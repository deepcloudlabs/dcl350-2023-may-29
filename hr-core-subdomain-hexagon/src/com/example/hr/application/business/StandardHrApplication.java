package com.example.hr.application.business;

import java.util.Optional;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.event.EmployeeEvent;
import com.example.hr.application.business.event.EmployeeFiredEvent;
import com.example.hr.application.business.event.EmployeeHiredEvent;
import com.example.hr.application.business.exception.ExistingEmployeeException;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.infrastructure.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

public class StandardHrApplication implements HrApplication {
	private final EmployeeRepository employeeRepository;
	private final EventPublisher<EmployeeEvent> eventPublisher;
	
	public StandardHrApplication(EmployeeRepository employeeRepository, EventPublisher<EmployeeEvent> eventPublisher) {
		this.employeeRepository = employeeRepository;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public Employee hireEmployee(Employee employee) {
		var identityNo = employee.getIdentityNo();
		if (employeeRepository.exists(identityNo))
			throw new ExistingEmployeeException(identityNo);		
		var persitedEmployee = employeeRepository.persist(employee);
		var employeeHiredEvent = new EmployeeHiredEvent(identityNo);
		eventPublisher.publish(employeeHiredEvent);
		return persitedEmployee; 
	}

	@Override
	public Optional<Employee> fireEmployee(TcKimlikNo identity) {		
		Optional<Employee> removedEmployee = employeeRepository.remove(identity);
		removedEmployee.ifPresent(employee -> {
			var employeeFiredEvent = new EmployeeFiredEvent(employee);
			this.eventPublisher.publish(employeeFiredEvent);
		});
		return removedEmployee;
	}

	@Override
	public Optional<Employee> findEmployee(TcKimlikNo identity) {
		return employeeRepository.findEmployeeByIdentity(identity);
	}

}
