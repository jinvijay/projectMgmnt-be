package com.fsd.pm.service;

import java.util.List;

import com.fsd.pm.service.dto.UserDto;

public interface UserService {

	public UserDto createUser(UserDto user);

	public List<UserDto> getUsers();

	public UserDto findById(int id);

	public UserDto update(UserDto user, int id);

	public void deleteUserById(int id);

}
