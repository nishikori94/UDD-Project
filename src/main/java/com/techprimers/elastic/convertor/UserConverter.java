package com.techprimers.elastic.convertor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.techprimers.elastic.dto.UserDTO;
import com.techprimers.elastic.model.User;
import com.techprimers.elastic.model.role.Role;

public class UserConverter {

	public UserConverter(){}
	
	public User DtoToUser(UserDTO dto,Role role) {
		return new User(dto.getUsername(), new BCryptPasswordEncoder().encode(dto.getPassword()), dto.getEmail(), dto.getName(), dto.getSurname(), role);
	}
}
