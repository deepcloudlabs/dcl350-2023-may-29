package com.example.hr.repository;

import static com.example.hexagonal.PortType.DRIVEN_PORT;

import java.util.Optional;

import com.example.ddd.Repository;
import com.example.hexagonal.Port;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

@Repository(forEntity = Employee.class)
@Port(type=DRIVEN_PORT)
public interface EmployeeRepository {

	Optional<Employee> findEmployeeByIdentity(TcKimlikNo identity);

	boolean exists(TcKimlikNo identityNo);

	Employee persist(Employee employee);

	Optional<Employee> remove(TcKimlikNo identity);

}
