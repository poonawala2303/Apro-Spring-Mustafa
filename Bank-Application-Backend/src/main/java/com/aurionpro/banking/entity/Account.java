package com.aurionpro.banking.entity;

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
@Table(name="bankaccount")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Account 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="accountNumber")
	private String accountNumber;
	
	@Column(name="balance")
	private double balance;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	
	@Column(name="is_deleted")
	private Boolean isDeleted = false;
}
