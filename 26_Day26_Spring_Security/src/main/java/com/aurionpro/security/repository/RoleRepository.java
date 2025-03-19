package com.aurionpro.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.security.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> 
{
	Optional<Role> findByRoleName(String role);
}
