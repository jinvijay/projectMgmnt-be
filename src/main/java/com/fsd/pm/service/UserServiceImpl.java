package com.fsd.pm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsd.pm.domain.User;
import com.fsd.pm.repo.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) {
		User savedUser = userRepository.save(user);
		return savedUser;

	}

	@Override
	public List<User> getUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User findById(int id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User update(User user, int id) {

		User userRecord = findById(id);
		userRecord.setEmpId(user.getEmpId());
		userRecord.setFirstName(user.getFirstName());
		userRecord.setLastName(user.getLastName());
		return userRepository.save(userRecord);
	}

	@Override
	public void deleteUserById(int id) {
		userRepository.deleteById(id);

	}

}
