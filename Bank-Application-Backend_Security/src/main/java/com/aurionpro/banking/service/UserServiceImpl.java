package com.aurionpro.banking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.banking.dto.PageResponse;
import com.aurionpro.banking.dto.UserRequestDto;
import com.aurionpro.banking.dto.UserResponseDto;
import com.aurionpro.banking.entity.Account;
import com.aurionpro.banking.entity.User;
import com.aurionpro.banking.exception.UserNotFoundException;
import com.aurionpro.banking.repository.AccountRepository;
import com.aurionpro.banking.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	private ModelMapper mapper;

	public UserServiceImpl() {
		
		this.mapper = new ModelMapper();
	}

	@Override
	public UserResponseDto addUser(UserRequestDto userRequest) {
		User dbUser = userRepository.save(mapper.map(userRequest, User.class));
		
		return mapper.map(dbUser, UserResponseDto.class);
	}

	@Override
	public PageResponse<UserResponseDto> getAllUsers(int pageSize, int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> users = null;
		
		users = userRepository.findAll(pageable);
		
		List<User> usersList = users.getContent();
		List<UserResponseDto> userResponse = new ArrayList<>();
		
		for(User user : usersList)
		{
			userResponse.add(mapper.map(user, UserResponseDto.class));
		}
		
		PageResponse<UserResponseDto> pageResponse = new PageResponse<>();
		
		pageResponse.setContent(userResponse);
		pageResponse.setLast(users.isLast());
		pageResponse.setPageSize(users.getSize());
		pageResponse.setTotalPages(users.getTotalPages());
		pageResponse.setTotalElements(users.getTotalElements());
		
		return pageResponse;
	}

	@Override
	public String deleteUser(int id) throws UserNotFoundException
	{
//		Optional.ofNullable(userRepository.findById(id).orElseThrow(()->
//		new UserNotFoundException("User with id - " +id + " dosent exist")));
//		
//		userRepository.deleteById(id);
		
		User user = userRepository.findById(id)
		            .orElseThrow(() -> new UserNotFoundException("User with id - \" +id + \" dosent exist"));

		user.setIsDeleted(true);
		userRepository.save(user);
		return "User marked as deleted.";
	}

	@Override
	public void deleteAllUsers() 
	{
		userRepository.deleteAll();	
	}

	@Override
	public UserResponseDto assignAccounts(int userId, List<Integer> accountIds) {
		
		Optional<User> dbUser = userRepository.findById(userId);
		
		if(dbUser.isEmpty())
			throw new RuntimeException("User with user id -"+userId + " does not exist");
		
		User user = dbUser.get();
		
		List<Account> accounts = new ArrayList<>();
		
		for(Integer accountId : accountIds)
		{
			Account account = accountRepository.findById(accountId)
					.orElseThrow(()-> new RuntimeException("Account with id - " + accountId + " does not exist"));
			
			account.setUser(user);
			Account dbAccount = accountRepository.save(account);
			accounts.add(dbAccount);
					
		}
		
		return mapper.map(userRepository.save(user),UserResponseDto.class);
	}

}
