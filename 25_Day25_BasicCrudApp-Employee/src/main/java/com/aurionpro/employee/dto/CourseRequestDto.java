package com.aurionpro.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class CourseRequestDto 
{
	
	private String courseName;
	
	private int duration;
	
	private double fees;
}
