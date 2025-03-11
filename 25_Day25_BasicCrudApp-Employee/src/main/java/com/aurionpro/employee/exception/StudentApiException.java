package com.aurionpro.employee.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class StudentApiException extends RuntimeException
{
	
	private static final long serialVersionUID = 1L;
	public String message;
	
	public String getMessage()
	{
		return "Student dosent Exist";
	}
}
