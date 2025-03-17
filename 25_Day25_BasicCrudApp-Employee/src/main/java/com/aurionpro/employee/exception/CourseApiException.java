package com.aurionpro.employee.exception;

public class CourseApiException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	public CourseApiException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
