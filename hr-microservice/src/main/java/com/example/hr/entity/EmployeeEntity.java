package com.example.hr.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
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

@Entity
@Table(name="employees")
@Data
public class EmployeeEntity {
	@TcKimlikNo
	@Id
	private String identityNo;
	@NotEmpty
	@Column(name="fname")
	private String firstName;
	@NotEmpty
	@Column(name="lname")
	private String lastName;
	@DecimalMin(value = "8500.0")
	private double salary;
	@NotNull
	private FiatCurrency currency;
	@Iban
	private String iban;
	@Max(2006)
	@Min(1940)
	@Column(name="byear")
	private int birthYear;
	@NotNull
	@ElementCollection
	private List<Department> departments;
	@Column(columnDefinition = "longblob")
	@Lob
	private byte[] photo;
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private JobStyle jobStyle;
}
