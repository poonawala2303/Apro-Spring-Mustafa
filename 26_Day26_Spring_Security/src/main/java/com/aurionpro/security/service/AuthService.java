package com.aurionpro.security.service;

import com.aurionpro.security.dto.LoginDto;
import com.aurionpro.security.dto.RegistrationDto;
import com.aurionpro.security.entity.User;

public interface AuthService 
{
	User register(RegistrationDto registration);
	String login(LoginDto loginDto);
	
}
