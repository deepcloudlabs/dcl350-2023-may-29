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
@Entity(identity = { "identityNo" }, aggregate = true)
public class Employee {
	private final TcKimlikNo identityNo;
	private FullName fullname;
	private Money salary;
	private Iban iban;
	private final BirthYear birthYear;
	private List<Department> departments;
	private Photo photo;
	private JobStyle jobStyle;

	public Employee(TcKimlikNo identityNo, FullName fullname, Money salary, Iban iban, BirthYear birthYear,
			List<Department> departments, Photo photo, JobStyle jobStyle) {
		this.identityNo = identityNo;
		this.fullname = fullname;
		this.salary = salary;
		this.iban = iban;
		this.birthYear = birthYear;
		this.departments = departments;
		this.photo = photo;
		this.jobStyle = jobStyle;
	}

	private Employee(Builder builder) {
		this.identityNo = builder.identityNo;
		this.fullname = builder.fullname;
		this.salary = builder.salary;
		this.iban = builder.iban;
		this.birthYear = builder.birthYear;
		this.departments = builder.departments;
		this.photo = builder.photo;
		this.jobStyle = builder.jobStyle;
	}

	public FullName getFullname() {
		return fullname;
	}

	public void setFullname(FullName fullname) {
		this.fullname = fullname;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public JobStyle getJobStyle() {
		return jobStyle;
	}

	public void setJobStyle(JobStyle jobStyle) {
		this.jobStyle = jobStyle;
	}

	public TcKimlikNo getIdentityNo() {
		return identityNo;
	}

	public Money getSalary() {
		return salary;
	}

	public Iban getIban() {
		return iban;
	}

	public BirthYear getBirthYear() {
		return birthYear;
	}

	public List<Department> getDepartments() {
		return List.copyOf(departments);
	}

	public void promoteTo(Department department) {
		this.departments.clear();
		this.departments.add(department);
		this.increaseSalary(Rate.PROMOTION_RATE);
	}

	public void increaseSalary(Rate rate) {
		// policies/business rules/invariants/constraints/...
		Objects.requireNonNull(rate);
		this.salary = this.salary.multiply(1.0 + rate.getNormalizedValue());
	}

	public static class Builder {
		private final TcKimlikNo identityNo;
		private FullName fullname;
		private Money salary;
		private Iban iban;
		private final BirthYear birthYear;
		private List<Department> departments;
		private Photo photo;
		private JobStyle jobStyle;

		public Builder(TcKimlikNo identityNo, BirthYear birthYear) {
			this.identityNo = identityNo;
			this.birthYear = birthYear;
		}

		public Builder fullname(String firstName, String lastName) {
			this.fullname = FullName.of(firstName, lastName);
			return this;
		}

		public Builder salary(FiatCurrency currency, double value) {
			this.salary = Money.valueOf(currency, value);
			return this;
		}

		public Builder salary(double value) {
			return salary(FiatCurrency.TL, value);
		}

		public Builder iban(String value) {
			this.iban = Iban.of(value);
			return this;
		}

		public Builder photo(byte[] values) {
			this.photo = Photo.of(values);
			return this;
		}

		public Builder photo(String base64Values) {
			this.photo = Photo.of(base64Values);
			return this;
		}

		public Builder jobStyle(String value) {
			this.jobStyle = JobStyle.valueOf(value);
			return this;
		}

		public Builder departments(String... values) {
			this.departments = List.of(values).stream().map(Department::valueOf).toList();
			return this;
		}

		public Employee build() {
			// Business Rule
			// Constraints
			// Policies
			for (var department : this.departments) {
				if (department.equals(Department.IT) && this.salary.lessThan(Money.valueOf(50_000)))
					throw new IllegalArgumentException("Violates POLICY-42");
			}
			// Validations
			// Invariants
			return new Employee(this);
		}
	}
}
