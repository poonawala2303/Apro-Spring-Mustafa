package com.aurionpro.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.crud.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>
{
	
}
