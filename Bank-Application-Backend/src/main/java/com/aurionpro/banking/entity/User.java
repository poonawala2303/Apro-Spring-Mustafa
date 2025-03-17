package com.aurionpro.banking.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name="users")
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private int user_id;
	
	@Column
	@NotBlank(message = "Name is compulsory")
	private String name;
	
	@Column(name="username")
	@NotBlank(message = "Username is compulsory")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	@Column
	private String email;
	
	@Column(name="role")
	private Role role;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Account> accounts;
}
