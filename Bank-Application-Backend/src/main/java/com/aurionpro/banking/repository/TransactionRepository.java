package com.aurionpro.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.banking.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

}
