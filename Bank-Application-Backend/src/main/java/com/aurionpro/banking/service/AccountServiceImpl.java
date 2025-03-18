package com.aurionpro.banking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.aurionpro.banking.dto.AccountRequestDto;
import com.aurionpro.banking.dto.AccountResponseDto;
import com.aurionpro.banking.dto.PageResponse;
import com.aurionpro.banking.entity.Account;
import com.aurionpro.banking.entity.ApiMessages;
import com.aurionpro.banking.entity.EmailDetails;
import com.aurionpro.banking.entity.Transaction;
import com.aurionpro.banking.exception.FundTransferException;
import com.aurionpro.banking.exception.InsufficientBalanceException;
import com.aurionpro.banking.exception.InvalidAmountException;
import com.aurionpro.banking.repository.AccountRepository;
import com.aurionpro.banking.repository.TransactionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService
{
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired 
	 
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}") private String sender;

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
	
	public String sendMail(EmailDetails details)
	{
		 
        try 
        {

            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

       
        catch (Exception e) {
            return "Error while Sending Mail";
        }
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

	@Transactional
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
	    
	    EmailDetails details = new EmailDetails();
	    details.setMsgBody("Dear Customer,\n\nYour deposit of " + amount + " has been successfully credited.\n" +
                "New Balance: " + total + "\n\nThank you for banking with us!");
	    details.setSubject("Deposit Notification");
	    details.setRecipient("poonawala2303@gmail.com");
	    
	    sendMail(details);
	    
		return accountToResponseDtoMapper(savedAccount);
		
	}
	
	@Transactional
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
	    
	    EmailDetails details = new EmailDetails();
	    details.setMsgBody("Dear Customer,\n\nYour withdrawl of " + amount + " has been debited.\n" +
                "New Balance: " + total + "\n\nThank you for banking with us!");
	    details.setSubject("Withdrawl Notification");
	    details.setRecipient("poonawala2303@gmail.com");
	    
	    sendMail(details);
		
		return accountToResponseDtoMapper(savedAccount);
	}

	@Transactional
	@Override
	public void fundTransfer(int srcAccId , int destAccId , double amount) throws AccountNotFoundException 
	{
		validateAmount(amount);
		
		Account srcAccount = accountRepository.findById(srcAccId).orElseThrow(()-> new AccountNotFoundException());
		Account destAccount = accountRepository.findById(destAccId).orElseThrow(()-> new AccountNotFoundException());
		
		if(srcAccId == destAccId)
		{
			throw new FundTransferException(ApiMessages.CASH_TRANSFER_SAME_ACCOUNT_ERROR.getMessage());
		}
		
		double sourceBalance = srcAccount.getBalance();
		
		if(sourceBalance < amount)
		{
			throw new InsufficientBalanceException(ApiMessages.BALANCE_INSUFFICIENT_ERROR.getMessage());
		}
		
		double newSourceBalance = sourceBalance - amount;
		srcAccount.setBalance(newSourceBalance);
		accountRepository.save(srcAccount);
		
		double targetAccBalance = destAccount.getBalance();
		double newTargetBalance = targetAccBalance + amount;
		destAccount.setBalance(newTargetBalance);
		accountRepository.save(destAccount);
				 
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setSenderAccno(srcAccount.getAccountNumber());
		transaction.setReceiverAccno(destAccount.getAccountNumber());
		transaction.setTransactionType("transfer");
		transaction.setDescription("Test Transaction");
		transactionRepository.save(transaction);
	}
	
	
	
}
