package com.fsd.pm.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fsd.pm.service.UserService;
import com.fsd.pm.service.dto.UserDto;

@RestController
@RequestMapping(value = { "/user" })
public class UsersController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

	@Autowired
	UserService userService;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") int id) {
		LOGGER.info("Fetching User with id " + id);
		UserDto user = userService.findById(id);
		if (user == null) {
			return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDto>(user, HttpStatus.OK);
	}

	@PostMapping(value = "/create", headers = "Accept=application/json")
	public ResponseEntity<Void> createUser(@RequestBody UserDto user, UriComponentsBuilder ucBuilder) {
		LOGGER.info("Creating User " + user.getFirstName());
		UserDto newUserDto = userService.createUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(newUserDto.getUserId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@GetMapping(value = "/get", headers = "Accept=application/json")
	public List<UserDto> getAllUser() {
		List<UserDto> users = userService.getUsers();
		return users;

	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateUser(@RequestBody UserDto currentUser) {
		UserDto user = userService.findById(currentUser.getUserId());
		if (user == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		userService.update(currentUser, currentUser.getUserId());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity<UserDto> deleteUser(@PathVariable("id") int id) {
		UserDto user = userService.findById(id);
		if (user == null) {
			return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
		}
		userService.deleteUserById(id);
		return new ResponseEntity<UserDto>(HttpStatus.NO_CONTENT);
	}

}
