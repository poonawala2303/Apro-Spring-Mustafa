package com.aurionpro.banking.controller;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.banking.dto.AccountRequestDto;
import com.aurionpro.banking.dto.AccountResponseDto;
import com.aurionpro.banking.dto.PageResponse;
import com.aurionpro.banking.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("bankapp/account")
public class AccountController 
{
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/accounts")
	public ResponseEntity<AccountResponseDto> addAccount(@RequestBody @Valid AccountRequestDto accountRequest)
	{
		return ResponseEntity.ok(accountService.addAccount(accountRequest));
	}
	
	@GetMapping("/accounts")
	public ResponseEntity<PageResponse<AccountResponseDto>> getAllAccounts(@RequestParam int pageSize , @RequestParam int pageNumber)
	{
		return ResponseEntity.ok(accountService.getAllAccounts(pageSize, pageNumber));
	}
	
	@GetMapping("/accounts/{id}")
	public ResponseEntity<AccountResponseDto> getAccountById(@PathVariable int id) throws AccountNotFoundException
	{
		return ResponseEntity.ok(accountService.getAccountById(id));
	}
	
	@DeleteMapping("/accounts/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable int id) throws AccountNotFoundException
	{
		accountService.deleteAccount(id);
		
		return ResponseEntity.ok("Account with id " + id + " deleted successfully");
	}
	
	@PostMapping("/deposit/{id}")
	public ResponseEntity<AccountResponseDto> cashDeposit(@PathVariable int id,@RequestParam double amount) throws AccountNotFoundException
	{
		return ResponseEntity.ok(accountService.cashDeposit(id, amount));
	}
	 
	@PostMapping("/withdraw/{id}")
	public ResponseEntity<AccountResponseDto> cashWithdrawl(@PathVariable int id,@RequestParam double amount) throws AccountNotFoundException
	{
		return ResponseEntity.ok(accountService.cashWithdrawl(id, amount));
	}
	 
	@PostMapping("/accounts/{srcAccId}/{destAccId}")
	public ResponseEntity<String> fundTransfer(@PathVariable int srcAccId , @PathVariable int destAccId , @RequestParam double amount) throws AccountNotFoundException
	{
		accountService.fundTransfer(srcAccId, destAccId, amount);
		 
		return ResponseEntity.ok("Fund Transfer Successfull");
	}
	 
//	@PostMapping("/sendMail")
//	public ResponseEntity<String> sendMail(@RequestBody EmailDetails details)
//	{
//	    accountService.sendMail(details);
//
//	    return ResponseEntity.ok("Mail sent");
//	}
	
}
