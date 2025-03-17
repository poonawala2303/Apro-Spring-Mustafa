package com.aurionpro.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.employee.dto.CourseRequestDto;
import com.aurionpro.employee.dto.CourseResponseDto;
import com.aurionpro.employee.dto.PageResponse;
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
	
	@GetMapping("/courses")
	public ResponseEntity<PageResponse<CourseResponseDto>> getAllCourses(@RequestParam int pageNumber , @RequestParam int pageSize)
	{
		return ResponseEntity.ok(courseService.getAllCourses(pageSize, pageNumber));
	}
	
	@GetMapping("/courses/{id}")
	public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable int id)
	{
		return ResponseEntity.ok(courseService.getCourseById(id));	
	}
	
	@GetMapping("/feessort")
	public ResponseEntity<PageResponse<CourseResponseDto>> getCourseByFeesOrderAsc(@RequestParam int pageNumber , @RequestParam int pageSize)
	{
		return ResponseEntity.ok(courseService.getCourseByFeesOrderAsc(pageSize, pageNumber));
	}
	
}
