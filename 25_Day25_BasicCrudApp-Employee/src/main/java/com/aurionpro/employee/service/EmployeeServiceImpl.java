package com.aurionpro.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.employee.entity.Employee;
import com.aurionpro.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;
	
	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = empRepo.findAll();
		return employees;
	}

}
