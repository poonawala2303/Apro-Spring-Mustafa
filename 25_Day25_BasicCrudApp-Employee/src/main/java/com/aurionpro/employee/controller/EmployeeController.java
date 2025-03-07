package com.aurionpro.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.employee.entity.Employee;
import com.aurionpro.employee.service.EmployeeService;

@RestController
@RequestMapping("/employeesapp")
public class EmployeeController 
{
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/employee")
	public List<Employee> getAllEmployee()
	{
		return empService.getAllEmployees();
	}
}
