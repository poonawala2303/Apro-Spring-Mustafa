package com.aurionpro.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.banking.dto.PageResponse;
import com.aurionpro.banking.dto.TransactionRequestDto;
import com.aurionpro.banking.dto.TransactionResponseDto;
import com.aurionpro.banking.service.TransactionService;

@RestController
@RequestMapping("/bankapp/transaction")
public class TransactionController 
{
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/transactions")
	public ResponseEntity<TransactionResponseDto> addTransaction(@RequestBody TransactionRequestDto transactionRequest)
	{
		return ResponseEntity.ok(transactionService.addTransaction(transactionRequest));
	}
	
	@GetMapping("/transactions")
	public ResponseEntity<PageResponse<TransactionResponseDto>> getAllTransactions(int pageSize , int pageNumber)
	{
		return ResponseEntity.ok(transactionService.getAllTransactions(pageSize, pageNumber));
	}
	
	@PutMapping("/transactions")
	public ResponseEntity<TransactionResponseDto> updateTransaction(@RequestBody TransactionRequestDto transactionRequest) {
		return ResponseEntity.ok(transactionService.addTransaction(transactionRequest));
	}
	
	@DeleteMapping("/transactions/{id}")
	public ResponseEntity<String> deleteTransactinById(@PathVariable int id)
	{
		transactionService.deleteTransactionById(id);
		
		return ResponseEntity.ok("Transaction with id - "+id+" deleted");
	}
	
	@DeleteMapping("/transactions")
	public ResponseEntity<String> deleteAllTransactions()
	{
		transactionService.deleteAllTransactions();
		
		return ResponseEntity.ok("All Transactions deleted");
	}
	
	
}
