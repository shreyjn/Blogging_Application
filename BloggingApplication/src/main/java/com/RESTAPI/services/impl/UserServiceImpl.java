package com.RESTAPI.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.RESTAPI.config.AppConstants;
import com.RESTAPI.entities.Role;
import com.RESTAPI.entities.User;
import com.RESTAPI.exceptions.ResourceNotFoundException;
import com.RESTAPI.payloads.UserDto;
import com.RESTAPI.repositories.RoleRepo;
import com.RESTAPI.repositories.UserRepo;
import com.RESTAPI.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToEntity(userDto);
		User saveduser = this.userRepo.save(user);
		return this.EntityToDto(saveduser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		User updatedUser = this.userRepo.save(user);

		return this.EntityToDto(updatedUser);
	}

	@Override
	public UserDto getSingleUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		return this.EntityToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.EntityToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		this.userRepo.delete(user);

	}

	public User dtoToEntity(UserDto userDto) {

		User user = this.mapper.map(userDto, User.class);
		/*
		 * user.setId(userDto.getId()); user.setName(userDto.getName());
		 * user.setEmail(userDto.getEmail()); user.setAbout(userDto.getAbout());
		 * user.setPassword(userDto.getPassword());
		 */
		return user;

	}

	public UserDto EntityToDto(User user) {
		UserDto userDto = this.mapper.map(user, UserDto.class);
		/*
		 * userDto.setId(user.getId()); userDto.setName(user.getName());
		 * userDto.setEmail(user.getEmail()); userDto.setAbout(user.getAbout());
		 * userDto.setPassword(user.getPassword());
		 */
		return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = this.mapper.map(userDto, User.class);

		// encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		// roles
		Role role=this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);

		User newUser = this.userRepo.save(user);
		return this.mapper.map(newUser, UserDto.class);

	}

}
