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
	
	@NotBlank(message = "Sender account number is required")
    @Column(nullable = false, length = 10)
    private String senderAccno;

    @Column(length = 10)
    private String receiverAccno;
	
    @Min(value = 1, message = "Amount must be at least 1")
	@Column
    private double amount;
	
    @NotBlank(message = "Transaction type is required")
	@Column
    private String transactionType; 
	
    @NotBlank(message = "Description is required")
    @Size(max = 255, message = "Description cannot exceed 255 characters")
    @Column
    private String description;
    
	@Column
    private LocalDateTime timestamp;
}
