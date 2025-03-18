package com.aurionpro.banking.service;

import com.aurionpro.banking.dto.PageResponse;
import com.aurionpro.banking.dto.TransactionRequestDto;
import com.aurionpro.banking.dto.TransactionResponseDto;
import com.aurionpro.banking.exception.TransactionNotFoundException;

public interface TransactionService 
{
	TransactionResponseDto addTransaction(TransactionRequestDto transactionRequest);
	PageResponse<TransactionResponseDto> getAllTransactions (int pageSize , int pageNumber);
	void deleteTransactionById(int id) throws TransactionNotFoundException;
	void deleteAllTransactions();
}
