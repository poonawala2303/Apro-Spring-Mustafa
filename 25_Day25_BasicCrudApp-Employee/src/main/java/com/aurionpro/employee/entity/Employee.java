package com.aurionpro.employee.entity;

import org.hibernate.annotations.Cascade;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	
	
}
