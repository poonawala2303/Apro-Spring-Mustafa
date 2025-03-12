package com.aurionpro.employee.service;

import com.aurionpro.employee.dto.CourseRequestDto;
import com.aurionpro.employee.dto.CourseResponseDto;

public interface CourseService 
{
	CourseResponseDto addCourse(CourseRequestDto courseRequest);
}
