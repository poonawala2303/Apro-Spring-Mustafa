package com.aurionpro.employee.service;

import com.aurionpro.employee.dto.InstructorRequestDto;
import com.aurionpro.employee.dto.InstructorResponseDto;


public interface InstructorService 
{
	InstructorResponseDto addInstructor(InstructorRequestDto requestDto);
}
