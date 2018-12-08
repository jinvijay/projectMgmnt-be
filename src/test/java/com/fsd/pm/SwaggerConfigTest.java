package com.fsd.pm;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerConfigTest {

	@InjectMocks
	private SwaggerConfig testObj;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testApi() {
		Docket response = testObj.api();
		assertThat(response, IsNull.notNullValue());

	}

}
