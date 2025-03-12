package com.aurionpro.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.employee.dto.CourseRequestDto;
import com.aurionpro.employee.dto.CourseResponseDto;
import com.aurionpro.employee.service.CourseService;

@RequestMapping("/employeesapp")
@RestController
public class CourseController 
{
	@Autowired
	private CourseService courseService;
	
	@PostMapping("/courses")
	public ResponseEntity<CourseResponseDto> addCourse( @RequestBody CourseRequestDto requestDto)
	{
		return ResponseEntity.ok(courseService.addCourse(requestDto));
	}
}
