package com.aurionpro.banking.service;

import javax.security.auth.login.AccountNotFoundException;

import com.aurionpro.banking.dto.AccountRequestDto;
import com.aurionpro.banking.dto.AccountResponseDto;
import com.aurionpro.banking.dto.PageResponse;

public interface AccountService 
{
	public AccountResponseDto addAccount(AccountRequestDto accountRequest);
	public PageResponse<AccountResponseDto> getAllAccounts(int pageSize , int pageNumber);
	public AccountResponseDto getAccountById(int id) throws AccountNotFoundException;
	public void deleteAccount(int id) throws AccountNotFoundException;
	public AccountResponseDto cashDeposit(int id, double amount) throws AccountNotFoundException;
	public AccountResponseDto cashWithdrawl(int id, double amount) throws AccountNotFoundException;
}
