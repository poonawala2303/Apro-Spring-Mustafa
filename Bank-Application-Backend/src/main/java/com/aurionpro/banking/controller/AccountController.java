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
import com.aurionpro.banking.entity.Account;
import com.aurionpro.banking.service.AccountService;

@RestController
@RequestMapping("bankapp/account")
public class AccountController 
{
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/addaccount")
	public ResponseEntity<AccountResponseDto> addAccount(@RequestBody AccountRequestDto accountRequest)
	{
		return ResponseEntity.ok(accountService.addAccount(accountRequest));
	}
	
	@GetMapping("/getaccount")
	public ResponseEntity<PageResponse<AccountResponseDto>> getAllAccounts(@RequestParam int pageSize , @RequestParam int pageNumber)
	{
		return ResponseEntity.ok(accountService.getAllAccounts(pageSize, pageNumber));
	}
	
	@GetMapping("/getaccount/{id}")
	public ResponseEntity<AccountResponseDto> getAccountById(@PathVariable int id) throws AccountNotFoundException
	{
		return ResponseEntity.ok(accountService.getAccountById(id));
	}
	
	@DeleteMapping("/deleteaccount/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable int id) throws AccountNotFoundException
	{
		accountService.deleteAccount(id);
		
		return ResponseEntity.ok("Account with id " + id + " deleted successfully");
	}
	
}
