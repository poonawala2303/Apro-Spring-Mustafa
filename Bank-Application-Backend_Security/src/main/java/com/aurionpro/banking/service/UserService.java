package com.aurionpro.banking.service;

import java.util.List;

import com.aurionpro.banking.dto.PageResponse;
import com.aurionpro.banking.dto.UserRequestDto;
import com.aurionpro.banking.dto.UserResponseDto;
import com.aurionpro.banking.entity.User;
import com.aurionpro.banking.exception.UserNotFoundException;

public interface UserService 
{
	UserResponseDto addUser(UserRequestDto userRequest);
	PageResponse<UserResponseDto> getAllUsers(int pageSize , int pageNumber);
	String deleteUser(int id) throws UserNotFoundException;
	void deleteAllUsers();
	UserResponseDto assignAccounts(int userId , List<Integer> accountIds);
}
