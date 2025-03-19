package com.aurionpro.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.security.dto.LoginDto;
import com.aurionpro.security.dto.RegistrationDto;
import com.aurionpro.security.entity.User;
import com.aurionpro.security.service.AuthService;

@RestController
@RequestMapping("/app")
public class UserController 
{
	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody RegistrationDto registration)
	{
		return ResponseEntity.ok(authService.register(registration));
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDto loginDto)
	{
		
		return ResponseEntity.ok(authService.login(loginDto));
	}
}
