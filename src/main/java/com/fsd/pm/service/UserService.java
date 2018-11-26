package com.fsd.pm.service;

import java.util.List;

import com.fsd.pm.domain.User;

public interface UserService {

	public User createUser(User user);

	public List<User> getUsers();

	public User findById(int id);

	public User update(User user, int id);

	public void deleteUserById(int id);

}
