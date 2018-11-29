package com.fsd.pm.service.transform;

import static org.junit.Assert.assertThat;

import java.util.Date;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import com.fsd.pm.domain.Project;
import com.fsd.pm.service.dto.ProjectDto;
import com.fsd.pm.service.dto.UserDto;

public class ProjectDtoTransformTest {

	ProjectDtoTransform testObj = new ProjectDtoTransform();

	@Test
	public void testApply() {
		ProjectDto projectDto = new ProjectDto();
		Date nowDate = new Date();
		projectDto.setEndDate(nowDate);
		projectDto.setStartDate(nowDate);
		projectDto.setManager(new UserDto());
		projectDto.setPriority(1);
		projectDto.setProject("ProjectName");
		projectDto.setProjectId(11);

		Project project = testObj.apply(projectDto);
		assertThat(project.getEndDate(), IsEqual.equalTo(nowDate));
		assertThat(project.getStartDate(), IsEqual.equalTo(nowDate));
		assertThat(project.getProject(), IsEqual.equalTo("ProjectName"));
		assertThat(project.getProjectId(), IsEqual.equalTo(0));
		assertThat(project.getManager(), IsEqual.equalTo(null));
	}
	
	@Test
	public void testApply_whenNull() {
		assertThat(testObj.apply(null), IsEqual.equalTo(null));
	}

}
