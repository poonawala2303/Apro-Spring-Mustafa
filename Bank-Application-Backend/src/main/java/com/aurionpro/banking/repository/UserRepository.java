package com.aurionpro.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.banking.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>
{
	
}
