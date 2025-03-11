package com.aurionpro.employee.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> 
{
//	List<Employee> findByName(String name);
	Page<Employee> findByName(String name,Pageable pageable);
}
