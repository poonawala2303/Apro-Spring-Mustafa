package com.aurionpro.employee.service;

import com.aurionpro.employee.dto.CourseRequestDto;
import com.aurionpro.employee.dto.CourseResponseDto;
import com.aurionpro.employee.dto.PageResponse;

public interface CourseService 
{
	CourseResponseDto addCourse(CourseRequestDto courseRequest);
	PageResponse<CourseResponseDto> getAllCourses(int pageSize,int pageNumber);
	CourseResponseDto getCourseById(int id);
	PageResponse<CourseResponseDto> getCourseByFeesOrderAsc(int pageSize , int pageNumber);
	
}
