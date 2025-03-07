package com.aurionpro.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> 
{

}
