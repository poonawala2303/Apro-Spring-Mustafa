package com.aurionpro.banking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.banking.dto.PageResponse;
import com.aurionpro.banking.dto.TransactionRequestDto;
import com.aurionpro.banking.dto.TransactionResponseDto;
import com.aurionpro.banking.entity.Transaction;
import com.aurionpro.banking.exception.TransactionNotFoundException;
import com.aurionpro.banking.repository.TransactionRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService
{
	@Autowired
	private TransactionRepository transactionRepo;
	
	public ModelMapper mapper;
	
	public TransactionServiceImpl() {
		super();
		this.mapper = new ModelMapper();
	}

	@Override
	public TransactionResponseDto addTransaction(TransactionRequestDto transactionRequest) 
	{
		
		Transaction transactionDb = transactionRepo.save(mapper.map(transactionRequest, Transaction.class));
		
		return mapper.map(transactionDb, TransactionResponseDto.class);
	}

	@Override
	public PageResponse<TransactionResponseDto> getAllTransactions(int pageSize, int pageNumber) 
	{
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Transaction> transactions = null;
		
		transactions = transactionRepo.findAll(pageable);
		
		List<Transaction> transactionsList = transactions.getContent();
		List<TransactionResponseDto> transactionDto = new ArrayList<>();
		
		for(Transaction transaction : transactionsList)
		{
			transactionDto.add(mapper.map(transaction,TransactionResponseDto.class));
		}
		
		PageResponse<TransactionResponseDto> pageResponse = new PageResponse<>();
		
		pageResponse.setContent(transactionDto);
		pageResponse.setLast(transactions.isLast());
		pageResponse.setPageSize(transactions.getSize());
		pageResponse.setTotalPages(transactions.getTotalPages());
		pageResponse.setTotalElements(transactions.getTotalElements());
		
		return pageResponse;
	}

	@Override
	public String deleteTransactionById(int id) throws TransactionNotFoundException 
	{
//		Optional.ofNullable(transactionRepo.findById(id).orElseThrow(()->
//		new TransactionNotFoundException("Transaction with id - " +id + " dosent exist")));
		
		Transaction transaction = transactionRepo.findById(id)
	            .orElseThrow(() -> new TransactionNotFoundException("Transaction with id - \" +id + \" dosent exist"));
		
		transaction.setIsDeleted(true);
	    transactionRepo.save(transaction);
	    return "Transaction marked as deleted.";
		
	}

	@Override
	public void deleteAllTransactions() 
	{
		transactionRepo.deleteAll();	
	}
	
}
