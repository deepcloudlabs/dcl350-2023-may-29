package com.example.hr.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.domain.Employee;
import com.example.hr.dto.response.EmployeeResponse;

@Configuration
public class ModelMapperConfig {
	private static final Converter<Employee,EmployeeResponse>
	EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER = context -> {
		var employee = context.getSource();
		var employeeResponse = new EmployeeResponse();
		employeeResponse.setIdentityNo(employee.getIdentityNo().getValue());
		employeeResponse.setFirstName(employee.getFullname().firstName());
		employeeResponse.setLastName(employee.getFullname().lastName());
		employeeResponse.setSalary(employee.getSalary().getValue());
		employeeResponse.setCurrency(employee.getSalary().getCurrency());
		employeeResponse.setIban(employee.getIban().getValue());
		employeeResponse.setBirthYear(employee.getBirthYear().value());
		employeeResponse.setDepartments(employee.getDepartments());
		employeeResponse.setJobStyle(employee.getJobStyle());
		employeeResponse.setPhoto(employee.getPhoto().getBase64Values());
		return employeeResponse;
	};
	
	@Bean
	ModelMapper createModelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER, Employee.class, EmployeeResponse.class);
		return modelMapper;
	}
}
