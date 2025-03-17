package com.aurionpro.employee.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class StudentErrorResponse 
{
	public String message;
	public int status;
	public long time;
}
