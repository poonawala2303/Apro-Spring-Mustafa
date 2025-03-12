package com.aurionpro.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.employee.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
