package com.aurionpro.employee.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="employees")
@AllArgsConstructor
@RequiredArgsConstructor
@Data

public class Employee
{
	
	@Column(name="employeeId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;
	@Column(name="name")
	@NotBlank(message = "Name must not be blank")
	private String name;
	@Column(name="salary")
	@Min(value = 15000)
	@Max(value = 700000)
	private int salary;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addreddId")
	private Address address;
	
	@ManyToMany
	@JoinTable(name = "student-course", joinColumns = @JoinColumn (name="studentId") , inverseJoinColumns = @JoinColumn (name="courseId"))
	private List<Course> courses;
	
	
}
