package com.aurionpro.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class InstructorResponseDto 
{
	private int instructorId;
	
	private String name;
	
	private String qualification;
	
	private int experience;
}
