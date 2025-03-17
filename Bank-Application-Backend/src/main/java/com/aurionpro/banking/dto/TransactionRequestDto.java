package com.aurionpro.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TransactionRequestDto 
{
	 private String senderAccno;
	 private String receiverAccno;
	 private double amount;
	 private String transactionType;
	 private String description;
}
