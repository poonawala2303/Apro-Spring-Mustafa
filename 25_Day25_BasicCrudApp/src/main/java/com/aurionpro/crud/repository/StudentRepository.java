package com.aurionpro.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.crud.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
