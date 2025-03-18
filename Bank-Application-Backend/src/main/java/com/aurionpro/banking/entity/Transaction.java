package com.aurionpro.banking.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="transaction")
public class Transaction 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
    @Column(name="sender_accno")
    private String senderAccno;

    @Column(name="reciever_accno")
    private String receiverAccno;
	
	@Column(name="amount")
    private double amount;

	@Column(name="transaction_type")
    private String transactionType; 
	
    @Column(name="description")
    private String description;
    
	@Column(name="timestamp")
    private LocalDateTime timestamp;
}
