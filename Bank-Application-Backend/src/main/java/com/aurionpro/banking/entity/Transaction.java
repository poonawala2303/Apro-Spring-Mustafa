package com.aurionpro.banking.entity;

import java.time.LocalDateTime;

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
	
	@ManyToOne
    @JoinColumn(name="sender_account_id")
    private Account senderAccount;

    @ManyToOne
    @JoinColumn(name="receiver_account_id")
    private Account receiverAccount;	
    
	@Column(name="amount")
    private double amount;

	@Column(name="transaction_type")
    private String transactionType; 
	
    @Column(name="description")
    private String description;
    
	@Column(name="timestamp")
    private LocalDateTime timestamp;
	
	@Column(name="is_deleted")
    private Boolean isDeleted = false;
	
}
