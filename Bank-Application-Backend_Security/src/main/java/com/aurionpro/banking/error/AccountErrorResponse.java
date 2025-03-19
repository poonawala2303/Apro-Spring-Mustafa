package com.aurionpro.banking.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class AccountErrorResponse 
{
	private String message;
	private int status;
	private long time;
}
