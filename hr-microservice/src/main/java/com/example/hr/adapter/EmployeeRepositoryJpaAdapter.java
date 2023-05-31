package com.example.hr.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.entity.EmployeeEntity;
import com.example.hr.repository.EmployeeEntityRepository;
import com.example.hr.repository.EmployeeRepository;

@Repository
public class EmployeeRepositoryJpaAdapter implements EmployeeRepository {
	private final EmployeeEntityRepository empRepo;
	private final ModelMapper modelMapper;
	
	public EmployeeRepositoryJpaAdapter(EmployeeEntityRepository empRepo, ModelMapper modelMapper) {
		this.empRepo = empRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public Optional<Employee> findEmployeeByIdentity(TcKimlikNo identity) {
		var employeeEntity = empRepo.findById(identity.getValue());
		return employeeEntity.map( ee -> modelMapper.map(ee, Employee.class));
	}

	@Override
	public boolean exists(TcKimlikNo identityNo) {
		return empRepo.existsById(identityNo.getValue());
	}

	@Override
	@Transactional
	public Employee persist(Employee employee) {
		var employeeEntity =modelMapper.map(employee, EmployeeEntity.class);
		var persistedEmployeeEntity = empRepo.save(employeeEntity );
		return modelMapper.map(persistedEmployeeEntity, Employee.class);
	}

	@Override
	@Transactional
	public Optional<Employee> remove(TcKimlikNo identity) {
		return empRepo.findById(identity.getValue())
				      .map(this::mapEmployeeEntityToEmployee);
	}

	public Employee mapEmployeeEntityToEmployee(EmployeeEntity entity) {
		return modelMapper.map(entity, Employee.class);
	}
}
