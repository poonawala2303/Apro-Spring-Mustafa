package com.aurionpro.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aurionpro.crud.entity.Student;


public interface StudentService {
	public List<Student> getAllStudents();
}
