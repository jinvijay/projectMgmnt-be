package com.fsd.pm.service.transform;

import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.HashSet;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
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
import com.fsd.pm.service.dto.TaskDto;
import com.fsd.pm.service.dto.UserDto;

public class TaskTransformTest {

	@Mock
	private UserTransform userTransform;

	@Mock
	private ProjectTransform projectTransform;

	@InjectMocks
	TaskTransform testObj;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() {
		Date nowDate = new Date();
		Task task = new Task();
		task.setChildTasks(new HashSet<>());
		task.setEndDate(nowDate);
		task.setParentTask(new Task());
		task.setPriority(1);
		task.setProject(new Project());
		task.setStartDate(nowDate);
		task.setStatus("WIP");
		task.setTask("TaskName");
		task.setTaskId(1);
		task.setUser(new User());

		// Mock
		ProjectDto parentProject = Mockito.mock(ProjectDto.class);
		when(projectTransform.apply(any(Project.class))).thenReturn(parentProject);

		UserDto user = Mockito.mock(UserDto.class);
		when(userTransform.apply(any(User.class))).thenReturn(user);

		TaskDto result = testObj.apply(task);
		assertThat(result.getEndDate(), IsEqual.equalTo(nowDate));
		assertThat(result.getParentTask(), IsNot.not(IsEqual.equalTo(null)));
	}

}
