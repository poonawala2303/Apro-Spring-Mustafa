package com.aurionpro.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.employee.dto.PageResponse;
import com.aurionpro.employee.dto.StudentDto;
import com.aurionpro.employee.entity.Employee;
import com.aurionpro.employee.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employeesapp")
public class EmployeeController 
{
	@Autowired
	private EmployeeService empService;
	
//	@GetMapping("/employee")
//	public ResponseEntity<List<Employee>> getAllEmployee()
//	{
//		return ResponseEntity.ok(empService.getAllEmployees());
//	}
	
//	@GetMapping("/employee")
//	public ResponseEntity<Page<Employee>> getAllEmployee(@RequestParam int pageNumber , @RequestParam int pageSize)
//	{
//		return ResponseEntity.ok(empService.getAllEmployees(pageNumber,pageSize));
//	}
	
//	@GetMapping("/employee")
//	public ResponseEntity<PageResponse<Employee>> getAllEmployee(@RequestParam int pageNumber , @RequestParam int pageSize , @RequestParam(required = false) String name)
//	{
//		return ResponseEntity.ok(empService.getAllEmployees(pageNumber,pageSize,name));
//	}
	
	@GetMapping("/employee")
	public ResponseEntity<PageResponse<StudentDto>> getAllEmployee(@RequestParam int pageNumber , @RequestParam int pageSize , @RequestParam(required = false) String name)
	{
		return ResponseEntity.ok(empService.getAllEmployees(pageNumber,pageSize,name));
	}
	
	@PostMapping("/employee")
	public ResponseEntity<Employee> addEmployeeRecord(@RequestBody @Valid Employee employee)
	{
		return ResponseEntity.ok(empService.addEmployee(employee)); 
	}
	
	@PutMapping("/employee")
	public ResponseEntity<Employee> updateEmployeeRecord(@RequestBody Employee employee)
	{
		return ResponseEntity.ok(empService.addEmployee(employee));
	}
	
	@DeleteMapping("/employee")
	public void deleteEmployeeRecord(@RequestBody Employee employee)
	{
		empService.deleteEmployee(employee);
	}
	
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<Employee> getAEmployeeRecord(@PathVariable int employeeId)
	{
		return ResponseEntity.ok(empService.getAStudent(employeeId));
	}
	
	
//	@GetMapping("/employeebyname")
//	public ResponseEntity<List<Employee>> getAEmployeeByNameRecord(@RequestParam String name)
//	{
//		return ResponseEntity.ok(empService.getEmployeeByName(name));
//	} 
	
	
}
