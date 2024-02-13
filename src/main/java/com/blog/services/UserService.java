package com.blog.services;

import java.util.List;

import com.blog.payloads.UserDto;

public interface UserService {

	//To create new user
	UserDto createUser(UserDto userDto);
	
	//To update user info
	UserDto updateUser(UserDto userDto, Integer userId);
	
	//To fetch user data from database
	UserDto getUserById(Integer userId);
	
	//To get all user data as list
	List<UserDto> getAllUsers();
	
	//To delete user data from database
	void deleteUser(Integer userId);
}
