package com.aurionpro.banking.exception;

import lombok.Data;

@Data
public class AccountNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public AccountNotFoundException(String message) {
		super(message); // Use super() instead of defining message separately
	}

}
