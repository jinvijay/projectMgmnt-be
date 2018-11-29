package com.fsd.pm.service.transform;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import com.fsd.pm.domain.User;
import com.fsd.pm.service.dto.UserDto;

public class UserDtoTransformTest {

	UserDtoTransform testObj = new UserDtoTransform();

	@Test
	public void test() {

		UserDto user = new UserDto();
		user.setEmpId(11);
		user.setFirstName("First");
		user.setLastName("Last");
		user.setUserId(11);

		User result = testObj.apply(user);
		assertThat(result.getEmpId(), IsEqual.equalTo(11));
		assertThat(result.getFirstName(), IsEqual.equalTo("First"));
		assertThat(result.getLastName(), IsEqual.equalTo("Last"));
		assertThat(result.getUserId(), IsEqual.equalTo(0));

	}

	@Test
	public void test_nullValue() {
		assertThat(testObj.apply(null), IsEqual.equalTo(null));
	}

}
