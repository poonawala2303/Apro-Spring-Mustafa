package com.aurionpro.employee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="course")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Course 
{
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseId;
	@Column
	private String courseName;
	@Column
	private int duration;
	@Column
	private double fees;
	
	@ManyToOne
	@JoinColumn(name = "instructorId")
	private Instructor instructor;
	
}
