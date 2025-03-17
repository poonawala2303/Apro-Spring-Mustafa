package com.aurionpro.employee.service;

import java.util.List;

import com.aurionpro.employee.dto.CourseResponseDto;
import com.aurionpro.employee.dto.InstructorRequestDto;
import com.aurionpro.employee.dto.InstructorResponseDto;
import com.aurionpro.employee.dto.PageResponse;


public interface InstructorService 
{
	InstructorResponseDto addInstructor(InstructorRequestDto requestDto);
	PageResponse<InstructorResponseDto> getAllInstructors(int pageNumber, int pageSize);
	InstructorResponseDto getInstructorById(int id);
	InstructorResponseDto assignCourses(int instructorId, List<Integer> courseIds);
	List<CourseResponseDto> getCourseByInstructor(int instructorId);
}
