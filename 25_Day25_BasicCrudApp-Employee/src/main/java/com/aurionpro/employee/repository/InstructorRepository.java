package com.aurionpro.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.employee.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Integer>{

}
