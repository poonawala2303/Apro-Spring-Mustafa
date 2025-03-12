package com.aurionpro.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.employee.dto.PageResponse;
import com.aurionpro.employee.dto.StudentDto;
import com.aurionpro.employee.entity.Employee;
import com.aurionpro.employee.exception.StudentApiException;
import com.aurionpro.employee.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
//	@Override
//	public Page<Employee> getAllEmployees(int pageNumber ,int pageSize) {
//		Pageable pageable = PageRequest.of(pageNumber, pageSize);
//		Page<Employee> employees = empRepo.findAll(pageable);
//		return employees;
//	}
	
//	@Override
//	public PageResponse<Employee> getAllEmployees(int pageNumber ,int pageSize) 
//	{
//		Pageable pageable = PageRequest.of(pageNumber, pageSize);
//		Page<Employee> employees = empRepo.findAll(pageable);
//		
//		PageResponse<Employee> pageResponse = new PageResponse<>();
//		
//		pageResponse.setPageSize(employees.getSize());
//		pageResponse.setLast(employees.isLast());
//		pageResponse.setTotalElements(employees.getTotalElements());
//		pageResponse.setTotalPages(employees.getTotalPages());
//		pageResponse.setContent(employees.getContent());
//		
//		return pageResponse;
//	}
	
//	@Override
//	public PageResponse<Employee> getAllEmployees(int pageNumber ,int pageSize,String name) 
//	{
//		Pageable pageable = PageRequest.of(pageNumber, pageSize);
//		Page<Employee> employees = null;
//		
//		if (name == null)
//		{
//			employees = empRepo.findAll(pageable);
//		}
//		
//		if(name!=null)
//		{
//			employees = empRepo.findByName(name,pageable);
//					
//		}
//		
//		PageResponse<Employee> pageResponse = new PageResponse<>();
//		
//		pageResponse.setPageSize(employees.getSize());
//		pageResponse.setLast(employees.isLast());
//		pageResponse.setTotalElements(employees.getTotalElements());
//		pageResponse.setTotalPages(employees.getTotalPages());
//		pageResponse.setContent(employees.getContent());
//		
//		return pageResponse;
//	}
	
	@Override
	public PageResponse<StudentDto> getAllEmployees(int pageNumber ,int pageSize,String name) 
	{
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Employee> employees = null;
		
		if (name == null)
		{
			employees = empRepo.findAll(pageable);
		}
		
		if(name!=null)
		{
			employees = empRepo.findByName(name,pageable);
					
		}
		
		
		List<Employee> dbEmp = employees.getContent();
		
		List<StudentDto> studDto = new ArrayList<>();
		
		for(Employee employee: dbEmp)
		{
			studDto.add(studentToStudentDtoMapper(employee));
		}
		
		
		PageResponse<StudentDto> pageResponse = new PageResponse<>();
		
		pageResponse.setPageSize(employees.getSize());
		pageResponse.setLast(employees.isLast());
		pageResponse.setTotalElements(employees.getTotalElements());
		pageResponse.setTotalPages(employees.getTotalPages());
//		pageResponse.setContent(employees.getContent());
		pageResponse.setContent(studDto);
		
		return pageResponse;
	}
	
	public StudentDto studentToStudentDtoMapper(Employee employee)
	{
		StudentDto dto = new StudentDto();
		
		dto.setEmployeeId(employee.getEmployeeId());
		dto.setName(employee.getName());
		dto.setSalary(employee.getSalary());
		
		return dto;
	}

	@Override
	public Employee addEmployee(Employee employee) {
		
		Employee empDb = empRepo.save(employee);
		
		log.info("Employee added successfully with ID: " + employee.getEmployeeId());
		
		return empDb;
	
	}

	@Override
	public void deleteEmployee(Employee employee) {
		empRepo.delete(employee);
	}

	@Override
	public Employee getAStudent(int employeeId) {
		Optional<Employee> empRecord =  empRepo.findById(employeeId);
		
		if(empRecord.isEmpty())
			throw new StudentApiException("Employee dosent exist");
		return empRecord.get();
	}

//	@Override
//	public List<Employee> getEmployeeByName(String name) {
//		
//		return empRepo.findByName(name);
//	}

}
