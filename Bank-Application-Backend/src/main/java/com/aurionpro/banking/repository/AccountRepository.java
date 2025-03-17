package com.aurionpro.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.banking.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>
{
	
}
