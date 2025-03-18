package com.aurionpro.banking.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.banking.dto.AccountRequestDto;
import com.aurionpro.banking.dto.AccountResponseDto;
import com.aurionpro.banking.dto.PageResponse;
import com.aurionpro.banking.entity.Account;
import com.aurionpro.banking.entity.ApiMessages;
import com.aurionpro.banking.entity.Transaction;
import com.aurionpro.banking.exception.InvalidAmountException;
import com.aurionpro.banking.repository.AccountRepository;
import com.aurionpro.banking.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService
{
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public AccountResponseDto addAccount(AccountRequestDto accountRequest) 
	{
		Account dbAccount = accountRepository.save(requestDtoToAccountMapper(accountRequest));
		
		return accountToResponseDtoMapper(dbAccount);
	}
	
	public Account requestDtoToAccountMapper(AccountRequestDto accountRequest)
	{
		Account account = new Account();
		account.setAccountNumber(accountRequest.getAccountNumber());
		account.setBalance(accountRequest.getBalance());
		
		return account;
	}
	
	public AccountResponseDto accountToResponseDtoMapper(Account account)
	{
		AccountResponseDto accountResponse = new AccountResponseDto();
		
		accountResponse.setId(account.getId());
		accountResponse.setAccountNumber(account.getAccountNumber());
		accountResponse.setBalance(account.getBalance());
		
		return accountResponse;
	}

	@Override
	public PageResponse<AccountResponseDto> getAllAccounts(int pageSize, int pageNumber) 
	{
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Account> accounts = null;
		
		accounts = accountRepository.findAll(pageable);
		
		List<Account> accountsList = accounts.getContent();
		List<AccountResponseDto> accountDto = new ArrayList<>();
		
		for(Account account : accountsList)
		{
			accountDto.add(accountToResponseDtoMapper(account));
		}
		
		PageResponse<AccountResponseDto> pageResponse = new PageResponse<>();
		
		pageResponse.setContent(accountDto);
		pageResponse.setLast(accounts.isLast());
		pageResponse.setPageSize(accounts.getSize());
		pageResponse.setTotalPages(accounts.getTotalPages());
		pageResponse.setTotalElements(accounts.getTotalElements());
		
		return pageResponse;
	}

	@Override
	public AccountResponseDto getAccountById(int id) throws AccountNotFoundException 
	{
		Optional<Account> accountDb = accountRepository.findById(id);
		
		if(accountDb.isEmpty())
		{
			throw new AccountNotFoundException("Account with this Id - " + id + " dosent exist");
		}
		
		AccountResponseDto response = accountToResponseDtoMapper(accountDb.get());
		
		return response;
	}

	@Override
	public void deleteAccount(int id) throws AccountNotFoundException
	{
		Optional.ofNullable(accountRepository.findById(id).orElseThrow(()->
		new AccountNotFoundException("Account with id - " +id + " dosent exist")));
//		accountRepository.findAll();
		
		accountRepository.deleteById(id);
		
	}
	
	private void validateAmount(double amount) 
	{
        if (amount <= 0) {
            throw new InvalidAmountException(ApiMessages.AMOUNT_NEGATIVE_ERROR.getMessage());
        }

        if (amount % 100 != 0) {
            throw new InvalidAmountException(ApiMessages.AMOUNT_NOT_MULTIPLE_OF_100_ERROR.getMessage());
        }

        if (amount > 100000) {
            throw new InvalidAmountException(ApiMessages.AMOUNT_EXCEED_100_000_ERROR.getMessage());
        }
    }

	@Override
	public AccountResponseDto cashDeposit(int id, double amount) throws AccountNotFoundException 
	{
		validateAmount(amount);
		
		Account dbAccount = accountRepository.findById(id).orElseThrow(()-> new AccountNotFoundException());
		
		double total = dbAccount.getBalance() + amount;
		dbAccount.setBalance(total);
		
		Account savedAccount = accountRepository.save(dbAccount);
		
		Transaction transaction = new Transaction();
		transaction.setSenderAccno(dbAccount.getAccountNumber());
	    transaction.setAmount(amount);
	    transaction.setTransactionType("deposit");
	    transactionRepository.save(transaction);
		
		return accountToResponseDtoMapper(savedAccount);
		
	}

	@Override
	public AccountResponseDto cashWithdrawl(int id, double amount) throws AccountNotFoundException 
	{
		validateAmount(amount);
		
		Account dbAccount = accountRepository.findById(id).orElseThrow(()-> new AccountNotFoundException());
		
		if(dbAccount.getBalance() < amount)
		{
			throw new RuntimeException("Insufficient amount in given account");
		}
		
		double total = dbAccount.getBalance() - amount;
		dbAccount.setBalance(total);
		
		Account savedAccount = accountRepository.save(dbAccount);
		
		Transaction transaction = new Transaction();
		transaction.setSenderAccno(dbAccount.getAccountNumber());
	    transaction.setAmount(amount);
	    transaction.setTransactionType("withdrawl");
	    transactionRepository.save(transaction);
		
		return accountToResponseDtoMapper(savedAccount);
	}
	
}
