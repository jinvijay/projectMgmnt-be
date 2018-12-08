package com.fsd.pm.service.transform;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Test;

import com.fsd.pm.domain.User;
import com.fsd.pm.service.dto.UserDto;

public class UserTransformTest {

	UserTransform testObj = new UserTransform();

	@Test
	public void test() {
		User user = new User();
		user.setEmpId(1);
		user.setFirstName("First");
		user.setLastName("Last");
		user.setUserId(2);

		UserDto result = testObj.apply(user);
		assertThat(result.getEmpId(), IsEqual.equalTo(1));
		assertThat(result.getFirstName(), IsEqual.equalTo("First"));
		assertThat(result.getLastName(), IsEqual.equalTo("Last"));
		assertThat(result.getUserId(), IsEqual.equalTo(2));
	}

	@Test
	public void test_whenNull() {
		assertThat(testObj.apply(null), IsNull.nullValue());
	}

}
