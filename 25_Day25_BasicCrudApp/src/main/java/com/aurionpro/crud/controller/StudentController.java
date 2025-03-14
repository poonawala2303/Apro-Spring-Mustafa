package com.aurionpro.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.crud.entity.Student;
import com.aurionpro.crud.service.StudentService;

@RestController
@RequestMapping("/studentsapp")
public class StudentController 
{
	
	@Autowired
	private StudentService service;
	
	@GetMapping("/students")
	public List<Student> getAllStudents()
	{
		return service.getAllStudents();
		
	}
	
}
