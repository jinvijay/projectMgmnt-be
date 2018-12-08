package com.fsd.pm.service.transform;

import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fsd.pm.domain.Project;
import com.fsd.pm.domain.Task;
import com.fsd.pm.domain.User;
import com.fsd.pm.service.dto.ProjectDto;
import com.fsd.pm.service.dto.UserDto;

public class ProjectTransformTest {

	@Mock
	private UserTransform userTransform;

	@InjectMocks
	ProjectTransform testObj;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() {
		Project project = new Project();
		Date nowDate = new Date();
		project.setEndDate(nowDate);
		project.setPriority(1);
		project.setProject("ProjectName");
		project.setProjectId(11);
		project.setStartDate(nowDate);
		
		Task task = new Task();
		task.setTaskId(1);
		project.setTasks(new HashSet<>(Arrays.asList(task)));

		// Mock
		UserDto mockUserDto = Mockito.mock(UserDto.class);
		when(userTransform.apply(any(User.class))).thenReturn(mockUserDto);

		ProjectDto projectDto = testObj.apply(project);
		assertThat(projectDto.getEndDate(), IsEqual.equalTo(nowDate));
		assertThat(projectDto.getManager(), IsEqual.equalTo(null));
		assertThat(projectDto.getProjectId(), IsEqual.equalTo(11));
		assertThat(projectDto.getProject(), IsEqual.equalTo("ProjectName"));
		assertThat(projectDto.getStartDate(), IsEqual.equalTo(nowDate));
		assertThat(projectDto.getPriority(), IsEqual.equalTo(1));

		project.setManager(new User());
		projectDto = testObj.apply(project);
		assertThat(projectDto.getEndDate(), IsEqual.equalTo(nowDate));
		assertThat(projectDto.getManager(), IsEqual.equalTo(mockUserDto));

	}

	@Test
	public void test_whenNull() {
		assertThat(testObj.apply(null), IsEqual.equalTo(null));
	}
	
	@Test
	public void test_taskNull() {
		Project project = new Project();
		Date nowDate = new Date();
		project.setEndDate(nowDate);
		project.setPriority(1);
		project.setProject("ProjectName");
		project.setProjectId(11);
		project.setStartDate(nowDate);
		project.setTasks(null);

		// Mock
		UserDto mockUserDto = Mockito.mock(UserDto.class);
		when(userTransform.apply(any(User.class))).thenReturn(mockUserDto);

		ProjectDto projectDto = testObj.apply(project);
		assertThat(projectDto.getEndDate(), IsEqual.equalTo(nowDate));
		assertThat(projectDto.getManager(), IsEqual.equalTo(null));
		assertThat(projectDto.getProjectId(), IsEqual.equalTo(11));
		assertThat(projectDto.getProject(), IsEqual.equalTo("ProjectName"));
		assertThat(projectDto.getStartDate(), IsEqual.equalTo(nowDate));
		assertThat(projectDto.getPriority(), IsEqual.equalTo(1));

		project.setManager(new User());
		projectDto = testObj.apply(project);
		assertThat(projectDto.getEndDate(), IsEqual.equalTo(nowDate));
		assertThat(projectDto.getManager(), IsEqual.equalTo(mockUserDto));

	}

}
