package com.aurionpro.banking.exception;

public class UserApiException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public UserApiException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
