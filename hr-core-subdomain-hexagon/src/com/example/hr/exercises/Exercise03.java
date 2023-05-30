package com.example.hr.exercises;

import com.example.hr.domain.BirthYear;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

public class Exercise03 {

	public static void main(String[] args) {
		var jack = new Employee(null, null, null, null, null, null, null, null);
		var kate = new Employee.Builder(TcKimlikNo.valueOf("11111111110"), new BirthYear(1988))
				               .fullname("Kate", "Austen")
				               .departments("FINANCE", "SALES")
				               .salary(100_000)
				               .iban("tr1")
				               .jobStyle("FULL_TIME")
				               .build();

	}

}
