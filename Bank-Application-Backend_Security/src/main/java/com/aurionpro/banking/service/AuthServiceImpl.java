package com.aurionpro.banking.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aurionpro.banking.dto.LoginDto;
import com.aurionpro.banking.dto.UserRequestDto;
import com.aurionpro.banking.dto.UserResponseDto;
import com.aurionpro.banking.entity.Role;
import com.aurionpro.banking.entity.User;
import com.aurionpro.banking.exception.UserApiException;
import com.aurionpro.banking.repository.RoleRepository;
import com.aurionpro.banking.repository.UserRepository;
import com.aurionpro.banking.security.JwtTokenProvider;


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
	
	@Autowired
	private ModelMapper mapper;
	
	public AuthServiceImpl() {
		super();
		this.mapper = new ModelMapper();
	}

	@Override
	public User register(UserRequestDto userRequestDto) 
	{
		if(userRepo.existsByUsername(userRequestDto.getUsername()))
			throw new UserApiException(HttpStatus.BAD_REQUEST, "User already Exists");
		
		User user = new User();
		user.setName(userRequestDto.getName());
		user.setUsername(userRequestDto.getUsername());
		user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
		user.setEmail(userRequestDto.getEmail());
		
		List<Role> roles = new ArrayList<>();
		
		Role userRole = roleRepo.findByRoleName(userRequestDto.getRoleName()).get();
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
