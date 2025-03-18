package com.aurionpro.banking.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ApiMessages 
{
	  AMOUNT_EXCEED_100_000_ERROR("Amount cannot be greater than 100,000"),
	  AMOUNT_INVALID_ERROR("Invalid amount"),
	  AMOUNT_NEGATIVE_ERROR("Amount must be greater than 0"),
	  AMOUNT_NOT_MULTIPLE_OF_100_ERROR("Amount must be in multiples of 100");
	
	  @Getter
	  private final String message;
}
