package com.aurionpro.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.crud.entity.Account;
import com.aurionpro.crud.service.AccountService;

@RestController
@RequestMapping("/accountsapp")
public class AccountController 
{
	@Autowired
	private AccountService service;
	
	@GetMapping("/account")
	public List<Account> getAllAccounts()
	{
		return service.getAllAccounts();
	}
}
