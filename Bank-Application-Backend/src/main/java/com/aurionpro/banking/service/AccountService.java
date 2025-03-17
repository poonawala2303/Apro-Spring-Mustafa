package com.aurionpro.banking.service;

import javax.security.auth.login.AccountNotFoundException;

import com.aurionpro.banking.dto.AccountRequestDto;
import com.aurionpro.banking.dto.AccountResponseDto;
import com.aurionpro.banking.dto.PageResponse;

public interface AccountService 
{
	AccountResponseDto addAccount(AccountRequestDto accountRequest);
	PageResponse<AccountResponseDto> getAllAccounts(int pageSize , int pageNumber);
	AccountResponseDto getAccountById(int id) throws AccountNotFoundException;
	void deleteAccount(int id) throws AccountNotFoundException;
}
