package com.aurionpro.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.banking.dto.PageResponse;
import com.aurionpro.banking.dto.UserRequestDto;
import com.aurionpro.banking.dto.UserResponseDto;
import com.aurionpro.banking.service.UserService;

@RestController
@RequestMapping("/bankapp/user")
public class UserController 
{
	@Autowired
	private UserService userService;
	
	@PostMapping("/addUser")
	public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto userRequest)
	{
		return ResponseEntity.ok(userService.addUser(userRequest));
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<PageResponse<UserResponseDto>> getAllUsers(int pageSize , int pageNumber)
	{
		return ResponseEntity.ok(userService.getAllUsers(pageSize, pageNumber));
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserRequestDto userRequest) {
		return ResponseEntity.ok(userService.addUser(userRequest));
	}

	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id) 
	{
		userService.deleteUser(id);
		
		return ResponseEntity.ok("User with id " + id + " deleted successfully");
	}
	
	@DeleteMapping("/deleteAllUsers")
	public ResponseEntity<String> deleteAllUsers()
	{
		userService.deleteAllUsers();
		
		return ResponseEntity.ok("All users deleted");
	}
	
}
