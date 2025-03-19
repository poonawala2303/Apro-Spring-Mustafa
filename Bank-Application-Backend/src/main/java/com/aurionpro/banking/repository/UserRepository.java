package com.aurionpro.banking.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.banking.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>
{
	List<User> findByIsDeletedFalse(Pageable pageable);
}
