package com.aurionpro.employee.service;

import java.util.List;

import com.aurionpro.employee.dto.PageResponse;
import com.aurionpro.employee.dto.StudentDto;
import com.aurionpro.employee.entity.Employee;

public interface EmployeeService 
{
	
//	public Page<Employee> getAllEmployees(int pageNumber , int pageSize);
//	public PageResponse<Employee> getAllEmployees(int pageNumber , int pageSize);
//	public PageResponse<Employee> getAllEmployees(int pageNumber , int pageSize,String name);
	
	public PageResponse<StudentDto> getAllEmployees(int pageNumber , int pageSize,String name);
	Employee addEmployee(Employee employee);
	void deleteEmployee(Employee employee);
	Employee getAStudent(int employeeId);
	
//	List<Employee> getEmployeeByName(String name);
}
