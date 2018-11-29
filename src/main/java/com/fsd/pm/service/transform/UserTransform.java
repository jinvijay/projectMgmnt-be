package com.fsd.pm.service.transform;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.fsd.pm.domain.User;
import com.fsd.pm.service.dto.UserDto;

@Component
public class UserTransform implements Function<User, UserDto> {

	@Override
	public UserDto apply(User t) {
		if (t == null) {
			return null;
		}
		
		UserDto newUser = new UserDto();
		newUser.setUserId(t.getUserId());
		newUser.setEmpId(t.getEmpId());
		newUser.setFirstName(t.getFirstName());
		newUser.setLastName(t.getLastName());

		return newUser;
	}

}
