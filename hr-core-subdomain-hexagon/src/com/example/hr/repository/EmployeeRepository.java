package com.example.hr.repository;

import java.util.Optional;

import com.example.ddd.Repository;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

@Repository(forEntity = Employee.class)
public interface EmployeeRepository {

	Optional<Employee> findEmployeeByIdentity(TcKimlikNo identity);

	boolean exists(TcKimlikNo identityNo);

	Employee persist(Employee employee);

	Optional<Employee> remove(TcKimlikNo identity);

}
