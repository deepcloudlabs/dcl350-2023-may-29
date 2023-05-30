package com.example.hr.application;

import java.util.Optional;

import com.example.ddd.Application;
import com.example.hr.application.business.StandardHrApplication;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

@Application(implementations = {
	StandardHrApplication.class	
})
public interface HrApplication {
	Employee hireEmployee(Employee employee);

	Optional<Employee> fireEmployee(TcKimlikNo identity);

	Optional<Employee> findEmployee(TcKimlikNo identity);
}
