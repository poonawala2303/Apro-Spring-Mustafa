package com.aurionpro.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.employee.dto.PageResponse;
import com.aurionpro.employee.entity.Employee;
import com.aurionpro.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;
	
//	@Override
//	public Page<Employee> getAllEmployees(int pageNumber ,int pageSize) {
//		Pageable pageable = PageRequest.of(pageNumber, pageSize);
//		Page<Employee> employees = empRepo.findAll(pageable);
//		return employees;
//	}
	
	@Override
	public PageResponse<Employee> getAllEmployees(int pageNumber ,int pageSize) 
	{
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Employee> employees = empRepo.findAll(pageable);
		
		PageResponse<Employee> pageResponse = new PageResponse<>();
		
		pageResponse.setPageSize(employees.getSize());
		pageResponse.setLast(employees.isLast());
		pageResponse.setTotalElements(employees.getTotalElements());
		pageResponse.setTotalPages(employees.getTotalPages());
		pageResponse.setContent(employees.getContent());
		
		return pageResponse;
	}

	@Override
	public Employee addEmployee(Employee employee) {
		
		return empRepo.save(employee);
	}

	@Override
	public void deleteEmployee(Employee employee) {
		empRepo.delete(employee);
	}

	@Override
	public Employee getAStudent(int employeeId) {
		Optional<Employee> empRecord =  empRepo.findById(employeeId);
		
		if(empRecord.isEmpty())
			throw new RuntimeException("Employee dosent exist");
		return empRecord.get();
	}

	@Override
	public List<Employee> getEmployeeByName(String name) {
		
		return empRepo.findByName(name);
	}

}
