package com.aurionpro.banking.service;

import com.aurionpro.banking.dto.LoginDto;
import com.aurionpro.banking.dto.UserRequestDto;
import com.aurionpro.banking.entity.User;

public interface AuthService 
{
	User register(UserRequestDto userRequestDto);
	String login(LoginDto loginDto);
	
}
