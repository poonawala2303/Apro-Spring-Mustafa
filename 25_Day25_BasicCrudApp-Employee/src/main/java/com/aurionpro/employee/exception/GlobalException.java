package com.aurionpro.employee.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aurionpro.employee.error.StudentErrorResponse;

@ControllerAdvice
public class GlobalException 
{
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleStudentApiException(StudentApiException e)
	{
		
		StudentErrorResponse response = new StudentErrorResponse();
		
		response.setMessage(e.getMessage());
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setTime(System.currentTimeMillis());
		
		return new ResponseEntity<StudentErrorResponse>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<?> handleStudentApiException(MethodArgumentNotValidException e)
	{
		
		Map<String, String> errors = new HashMap<>();
		
		e.getBindingResult().getFieldErrors().forEach((error)->{
			errors.put(error.getField(),error.getDefaultMessage());
		});
		
	
		
		return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
	}
}
