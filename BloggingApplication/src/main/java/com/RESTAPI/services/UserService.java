package com.RESTAPI.services;

import java.util.List;

import com.RESTAPI.payloads.UserDto;

public interface UserService {
	UserDto registerNewUser(UserDto user);

	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, Integer userId);

	UserDto getSingleUser(Integer userId);

	List<UserDto> getAllUsers();

	void deleteUser(Integer userId);

}
