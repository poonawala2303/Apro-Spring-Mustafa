package com.aurionpro.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.banking.dto.LoginDto;
import com.aurionpro.banking.dto.UserRequestDto;
import com.aurionpro.banking.dto.UserResponseDto;
import com.aurionpro.banking.entity.User;
import com.aurionpro.banking.service.AuthService;


@RestController
@RequestMapping("/app")
public class AuthController 
{
	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody UserRequestDto userRequestDto)
	{
		return ResponseEntity.ok(authService.register(userRequestDto));
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDto loginDto)
	{
		
		return ResponseEntity.ok(authService.login(loginDto));
	}
	
}
