package com.aurionpro.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class StudentDto 
{
	
	private int employeeId;
	
	private String name;
	
	private int salary;
}
