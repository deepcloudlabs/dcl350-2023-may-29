package com.example.hr.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.domain.BirthYear;
import com.example.hr.domain.Department;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.entity.EmployeeEntity;

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
	private static final Converter<Employee,EmployeeEntity>
	EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER = context -> {
		var employee = context.getSource();
		var employeeEntity = new EmployeeEntity();
		employeeEntity.setIdentityNo(employee.getIdentityNo().getValue());
		employeeEntity.setFirstName(employee.getFullname().firstName());
		employeeEntity.setLastName(employee.getFullname().lastName());
		employeeEntity.setSalary(employee.getSalary().getValue());
		employeeEntity.setCurrency(employee.getSalary().getCurrency());
		employeeEntity.setIban(employee.getIban().getValue());
		employeeEntity.setBirthYear(employee.getBirthYear().value());
		employeeEntity.setDepartments(employee.getDepartments());
		employeeEntity.setJobStyle(employee.getJobStyle());
		employeeEntity.setPhoto(employee.getPhoto().getValues());
		return employeeEntity;
	};	
	private static final Converter<HireEmployeeRequest,Employee>
	HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER = context -> {
		var request = context.getSource();
		var employee = new Employee.Builder(TcKimlikNo.valueOf(request.getIdentityNo()), new BirthYear(request.getBirthYear()))
		                    .fullname(request.getFirstName(),request.getLastName())
		                    .salary(request.getCurrency(),request.getSalary())
		                    .iban(request.getIban())
		                    .departments(request.getDepartments().stream().map(Department::name).toList().toArray(new String[0]))
		                    .jobStyle(request.getJobStyle().name())
		                    .photo(request.getPhoto())
		                    .build();
		return employee;
	};	
	private static final Converter<EmployeeEntity,Employee>
	EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER = context -> {
		var request = context.getSource();
		var employee = new Employee.Builder(TcKimlikNo.valueOf(request.getIdentityNo()), new BirthYear(request.getBirthYear()))
		                    .fullname(request.getFirstName(),request.getLastName())
		                    .salary(request.getCurrency(),request.getSalary())
		                    .iban(request.getIban())
		                    .departments(request.getDepartments().stream().map(Department::name).toList().toArray(new String[0]))
		                    .jobStyle(request.getJobStyle().name())
		                    .photo(request.getPhoto())
		                    .build();
		return employee;
	};	
	@Bean
	ModelMapper createModelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER, Employee.class, EmployeeResponse.class);
		modelMapper.addConverter(HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER, HireEmployeeRequest.class, Employee.class);
		modelMapper.addConverter(EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER, EmployeeEntity.class, Employee.class);
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER, Employee.class, EmployeeEntity.class);
		return modelMapper;
	}
}
