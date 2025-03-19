package com.aurionpro.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AccountRequestDto 
{
	private String accountNumber;
	
	private double balance;
}
