package com.aurionpro.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class JwtAuthResponse 
{
	private String token;
	private String tokenType="bearer";
}
