package com.example.hr.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.service.HrService;
import com.example.validation.TcKimlikNo;

@RestController
@RequestScope
@RequestMapping("/employees")
@CrossOrigin
@Validated
public class HrRestController {
	private final HrService hrService;
	
	public HrRestController(HrService hrService) {
		this.hrService = hrService;
	}

	//         application.properties|
	// GET http://localhost:9100/hr/api/v1/employees/11111111110
	@GetMapping("{identityNo}")
	public EmployeeResponse findEmployee(
			@PathVariable @TcKimlikNo String identityNo) {
		return hrService.getEmployeeByIdentity(identityNo);
	}
	
	// POST http://localhost:9100/hr/api/v1/employees
	@PostMapping
	public HireEmployeeResponse hireEmployee(
			@RequestBody @Validated HireEmployeeRequest employee) {
		return hrService.hireEmployee(employee);
	}

	@DeleteMapping("{identityNo}")
	public EmployeeResponse fireEmployee(
			@PathVariable @TcKimlikNo String identityNo) {
		return hrService.fireEmployee(identityNo);		
	}

}
