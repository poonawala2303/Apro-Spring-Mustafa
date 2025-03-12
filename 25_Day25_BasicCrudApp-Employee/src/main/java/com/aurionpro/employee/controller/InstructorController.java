package com.aurionpro.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.employee.dto.InstructorRequestDto;
import com.aurionpro.employee.dto.InstructorResponseDto;
import com.aurionpro.employee.service.InstructorService;

@RequestMapping("/employeesapp")
@RestController
public class InstructorController 
{
	@Autowired
	private InstructorService instructorService;
	
	@PostMapping("/instructors")
	public ResponseEntity<InstructorResponseDto> addInstructor( @RequestBody InstructorRequestDto requestDto)
	{
		return ResponseEntity.ok(instructorService.addInstructor(requestDto));
	}
}
