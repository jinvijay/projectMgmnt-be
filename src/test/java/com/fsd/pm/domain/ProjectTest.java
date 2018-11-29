package com.fsd.pm.domain;

import static org.junit.Assert.assertThat;

import java.util.Date;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

public class ProjectTest {

	@Test
	public void test() {
		Project testObj = new Project();

		Date sampleDate = new Date();
		testObj.setPriority(1);
		testObj.setProject("Project Name");
		testObj.setEndDate(sampleDate);
		testObj.setProjectId(1);
		testObj.setStartDate(sampleDate);
		testObj.setTasks(null);

		assertThat(testObj.getPriority(), IsEqual.equalTo(1));
		assertThat(testObj.getProject(), IsEqual.equalTo("Project Name"));
		assertThat(testObj.getEndDate(), IsEqual.equalTo(sampleDate));
		assertThat(testObj.getProjectId(), IsEqual.equalTo(1));
		assertThat(testObj.getStartDate(), IsEqual.equalTo(sampleDate));
		assertThat(testObj.getTasks(), IsEqual.equalTo(null));

	}

}
