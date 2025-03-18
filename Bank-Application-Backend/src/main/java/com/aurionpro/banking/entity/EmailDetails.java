package com.aurionpro.banking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class EmailDetails 
{
	private String recipient;
    private String msgBody;
    private String subject;
}
