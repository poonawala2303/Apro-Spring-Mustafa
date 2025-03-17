package com.aurionpro.banking.dto;

import com.aurionpro.banking.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserRequestDto 
{
	private String name;
    private String username;
    private String password;
    private String email;
    private Role role;
}
