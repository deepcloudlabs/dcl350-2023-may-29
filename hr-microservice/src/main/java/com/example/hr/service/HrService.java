package com.example.hr.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.application.HrApplication;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;

@Service
public class HrService {
	private final HrApplication hrApplication;
	private final ModelMapper modelMapper;
	
	public HrService(HrApplication hrApplication, ModelMapper modelMapper) {
		this.hrApplication = hrApplication;
		this.modelMapper = modelMapper;
	}

	public EmployeeResponse getEmployeeByIdentity(String identityNo) {
		var employee = hrApplication.findEmployee(TcKimlikNo.valueOf(identityNo))
				                    .orElseThrow(() -> new IllegalArgumentException("Employee with the identit no (%s) does not exist.".formatted(identityNo)));
		return modelMapper.map(employee, EmployeeResponse.class);
	}

	@Transactional
	@Bulkhead(name = "hiringBulkHead", type = Type.THREADPOOL)
	public HireEmployeeResponse hireEmployee(HireEmployeeRequest request) {
		var employee = modelMapper.map(request, Employee.class);
		hrApplication.hireEmployee(employee);
		return new HireEmployeeResponse("success");
	}

	@Transactional
	public EmployeeResponse fireEmployee(String identityNo) {
		var employee = hrApplication.fireEmployee(TcKimlikNo.valueOf(identityNo))
                .orElseThrow(() -> new IllegalArgumentException("Employee with the identit no (%s) does not exist.".formatted(identityNo)));
		return modelMapper.map(employee, EmployeeResponse.class);
	}

}
