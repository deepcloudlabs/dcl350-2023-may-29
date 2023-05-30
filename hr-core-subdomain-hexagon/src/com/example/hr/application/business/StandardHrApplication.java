package com.example.hr.application.business;

import java.util.Optional;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.exception.ExistingEmployeeException;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.repository.EmployeeRepository;

public class StandardHrApplication implements HrApplication {
	private final EmployeeRepository employeeRepository;
	
	public StandardHrApplication(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee hireEmployee(Employee employee) {
		var identityNo = employee.getIdentityNo();
		if (employeeRepository.exists(identityNo))
			throw new ExistingEmployeeException(identityNo);		
		return employeeRepository.persist(employee);
	}

	@Override
	public Optional<Employee> fireEmployee(TcKimlikNo identity) {		
		return employeeRepository.remove(identity);
	}

	@Override
	public Optional<Employee> findEmployee(TcKimlikNo identity) {
		return employeeRepository.findEmployeeByIdentity(identity);
	}

}
