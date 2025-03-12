package com.aurionpro.employee.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="instructor")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Instructor 
{
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int instructorId;
	@Column
	private String name;
	@Column
	private String qualification;
	@Column
	private int experience;
	
	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH},mappedBy = "instructor")
	private List<Course> courses;
	
}
