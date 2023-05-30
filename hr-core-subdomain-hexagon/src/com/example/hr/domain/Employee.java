package com.example.hr.domain;

import java.util.List;
import java.util.Objects;

import com.example.ddd.Entity;
// Domain Expert -- communication --> Ubiq. Lang.
// Ubiquitous Language: Employee, TcKimlikNo, FullName, Money, 
//                      BirthYear, JobStyle, Photo, Iban
// Java Language -> OOP -> Class
// Aggregate (Entity Root), Entity, Value Object
// Entity Class: i) Identity ii) Mutable
@Entity(identity = {"identityNo"})
public class Employee {
    private TcKimlikNo identityNo;
    private FullName fullname;
    private Money salary;
    private Iban iban;
    private BirthYear birthYear;
    private List<Department> departments;
    private Photo photo;
    private JobStyle jobStyle;
    
    public void increaseSalary(Rate rate) {
    	// policies/business rules/invariants/constraints/...
    	Objects.requireNonNull(rate);
    	this.salary = this.salary.multiply(1.0 + rate.getNormalizedValue());
    }
}
