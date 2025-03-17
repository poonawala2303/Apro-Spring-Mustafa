package com.aurionpro.banking.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TransactionResponseDto 
{
	private int transactionId;
    private String senderAccno;
    private String receiverAccno;
    private Double amount;
    private String transactionType;
    private String description;
    private LocalDateTime timestamp;
}
