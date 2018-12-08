package com.fsd.pm.service;

import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fsd.pm.domain.User;
import com.fsd.pm.repo.UserRepository;
import com.fsd.pm.service.dto.UserDto;
import com.fsd.pm.service.transform.UserDtoTransform;
import com.fsd.pm.service.transform.UserTransform;

public class UserServiceImplTest {

	@Mock
	private UserDtoTransform userDtoTransform;

	@Mock
	private UserTransform userTransform;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl testObj;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateUser() {
		UserDto userDto = new UserDto();

		User user = Mockito.mock(User.class);
		when(userDtoTransform.apply(userDto)).thenReturn(user);

		User savedUser = Mockito.mock(User.class);
		when(userRepository.save(any(User.class))).thenReturn(savedUser);
		UserDto savedUserDto = Mockito.mock(UserDto.class);
		when(userTransform.apply(savedUser)).thenReturn(savedUserDto);
		UserDto result = testObj.createUser(userDto);

		assertThat(result, IsEqual.equalTo(savedUserDto));
	}

	@Test
	public void testGetUsers() {
		List<User> usersList = new ArrayList<>();
		when(userRepository.findAll()).thenReturn(usersList);
		List<UserDto> result = testObj.getUsers();

		assertThat(result, IsNull.notNullValue());
	}

	@Test
	public void testFindById() {

		User user = Mockito.mock(User.class);
		when(userRepository.findById(1)).thenReturn(Optional.of(user));
		UserDto userDto = Mockito.mock(UserDto.class);
		when(userTransform.apply(user)).thenReturn(userDto);

		UserDto result = testObj.findById(1);
		assertThat(result, IsEqual.equalTo(userDto));

		when(userRepository.findById(1)).thenReturn(Optional.ofNullable(null));
		result = testObj.findById(1);
		assertThat(result, IsEqual.equalTo(null));

	}

	@Test
	public void testUpdate() {
		UserDto userDto = new UserDto();

		User user = Mockito.mock(User.class);
		when(userRepository.findById(1)).thenReturn(Optional.of(user));

		User savedUser = Mockito.mock(User.class);
		when(userRepository.save(any(User.class))).thenReturn(savedUser);
		UserDto savedUserDto = Mockito.mock(UserDto.class);
		when(userTransform.apply(savedUser)).thenReturn(savedUserDto);
		UserDto result = testObj.update(userDto, 1);

		assertThat(result, IsEqual.equalTo(savedUserDto));
	}

	@Test(expected = ValidationException.class)
	public void testUpdate_whenNoProject() {
		UserDto userDto = new UserDto();
		when(userRepository.findById(1)).thenReturn(Optional.ofNullable(null));
		testObj.update(userDto, 1);
	}

	@Test
	public void testDeleteUserById() {
		testObj.deleteUserById(1);
	}

}
