package com.aurionpro.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AccountResponseDto 
{
	private int id;
	
	private String accountNumber;
	
	private double balance;
}
