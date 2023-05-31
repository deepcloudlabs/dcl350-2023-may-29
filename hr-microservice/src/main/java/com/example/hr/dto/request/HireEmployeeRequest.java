package com.example.hr.dto.request;

import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;
import com.example.validation.Iban;
import com.example.validation.TcKimlikNo;

import lombok.Data;

@Data
public class HireEmployeeRequest {
	@TcKimlikNo
	private String identityNo;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@DecimalMin(value = "8500.0")
	private double salary;
	@NotNull
	private FiatCurrency currency;
	@Iban
	private String iban;
	@Max(2006)
	@Min(1940)
	private int birthYear;
	@NotNull
	private List<Department> departments;
	private String photo;
	@NotNull
	private JobStyle jobStyle;
}
