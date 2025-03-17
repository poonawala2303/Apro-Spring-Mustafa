package com.aurionpro.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.employee.dto.CourseResponseDto;
import com.aurionpro.employee.dto.InstructorRequestDto;
import com.aurionpro.employee.dto.InstructorResponseDto;
import com.aurionpro.employee.dto.PageResponse;
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
	
	@GetMapping("/instructors")
	public ResponseEntity<PageResponse<InstructorResponseDto>> getAllInstructors(@RequestParam int pageNumber, @RequestParam int pageSize)
	{
		return ResponseEntity.ok(instructorService.getAllInstructors(pageNumber,pageSize));
	}
	
	@GetMapping("/instructors/{id}")
	public ResponseEntity<InstructorResponseDto> getInstructorById(@PathVariable int id)
	{
		return ResponseEntity.ok(instructorService.getInstructorById(id));
	}
	
	@PutMapping("/instructors/{instructorId}")
	public ResponseEntity<InstructorResponseDto> assignCourses(@PathVariable int instructorId, @RequestBody List<Integer> courseIds)
	{
		return ResponseEntity.ok(instructorService.assignCourses(instructorId,courseIds));
	}
	
	@GetMapping("/coursetaughtbyinstructor/{instructorId}")
	public ResponseEntity<List<CourseResponseDto>> getCourseByInstructor(@PathVariable int instructorId)
	{
		return ResponseEntity.ok(instructorService.getCourseByInstructor(instructorId));
	}
	
}
