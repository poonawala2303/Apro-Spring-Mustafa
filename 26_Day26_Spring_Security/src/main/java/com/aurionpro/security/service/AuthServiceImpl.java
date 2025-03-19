package com.aurionpro.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.aurionpro.security.dto.LoginDto;
import com.aurionpro.security.dto.RegistrationDto;
import com.aurionpro.security.entity.Role;
import com.aurionpro.security.entity.User;
import com.aurionpro.security.exception.UserApiException;
import com.aurionpro.security.repository.RoleRepository;
import com.aurionpro.security.repository.UserRepository;
import com.aurionpro.security.security.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Override
	public User register(RegistrationDto registration) 
	{
		if(userRepo.existsByUsername(registration.getUsername()))
			throw new UserApiException(HttpStatus.BAD_REQUEST, "User already Exists");
		
		User user = new User();
		user.setUsername(registration.getUsername());
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		
		List<Role> roles = new ArrayList<>();
		
		Role userRole = roleRepo.findByRoleName(registration.getRole()).get();
		roles.add(userRole);
		user.setRoles(roles);
		
		return userRepo.save(user);
	}

	@Override
	public String login(LoginDto loginDto)
	{
		try
		{
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token = tokenProvider.generateToken(authentication);
			return token;
		}
		
		catch(BadCredentialsException e)
		{
			throw new UserApiException(HttpStatus.NOT_FOUND,"Username or Password is incorrect");
		}
		
		
	}

}
