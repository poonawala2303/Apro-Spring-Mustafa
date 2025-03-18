package com.aurionpro.banking.exception;

public class TransactionNotFoundException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public TransactionNotFoundException(String message) {
		super();
		this.message = message;
	}

	private String message;
	
	public String getMessage()
	{
		return message;
	}

}
