package com.fsd.pm.service;

import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.fsd.pm.domain.Project;
import com.fsd.pm.domain.Task;
import com.fsd.pm.domain.User;
import com.fsd.pm.repo.ProjectRepository;
import com.fsd.pm.repo.TaskRepository;
import com.fsd.pm.repo.UserRepository;
import com.fsd.pm.service.dto.ProjectDto;
import com.fsd.pm.service.dto.TaskDto;
import com.fsd.pm.service.dto.UserDto;
import com.fsd.pm.service.transform.TaskDtoTransform;
import com.fsd.pm.service.transform.TaskTransform;

public class TaskServiceImplTest {

	@Mock
	private TaskTransform taskTransform;

	@Mock
	private TaskDtoTransform taskDtoTransform;

	@Mock
	private TaskRepository taskRepository;

	@Mock
	private ProjectRepository projectRepository;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private TaskServiceImpl testObj;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateTask() {
		TaskDto taskDto = new TaskDto();
		ProjectDto projectDto = new ProjectDto();
		projectDto.setProjectId(1);
		taskDto.setProject(projectDto);

		UserDto userDto = new UserDto();
		userDto.setUserId(1);
		taskDto.setUser(userDto);

		Project projectRecord = Mockito.mock(Project.class);
		when(projectRepository.findById(1)).thenReturn(Optional.of(projectRecord));
		User userRecord = Mockito.mock(User.class);
		when(userRepository.findById(1)).thenReturn(Optional.of(userRecord));
		Task savedTask = Mockito.mock(Task.class);
		when(taskRepository.save(any(Task.class))).thenReturn(savedTask);
		TaskDto savedTaskDto = Mockito.mock(TaskDto.class);
		when(taskTransform.apply(savedTask)).thenReturn(savedTaskDto);
		TaskDto result = testObj.createTask(taskDto);

		assertThat(result, IsEqual.equalTo(savedTaskDto));
	}

	@Test
	public void testGetTasks() {
		List<Task> tasksList = new ArrayList<>();
		when(taskRepository.findAll()).thenReturn(tasksList);
		List<TaskDto> result = testObj.getTasks();

		assertThat(result, IsNull.notNullValue());
	}

	@Test
	public void testFindById() {
		Task task = Mockito.mock(Task.class);
		when(taskRepository.findById(1)).thenReturn(Optional.of(task));

		TaskDto taskDto = Mockito.mock(TaskDto.class);
		when(taskTransform.apply(task)).thenReturn(taskDto);

		TaskDto result = testObj.findById(1);
		assertThat(result, IsEqual.equalTo(taskDto));
	}

	@Test
	public void testUpdate() {
		TaskDto taskDto = new TaskDto();
		ProjectDto projectDto = new ProjectDto();
		projectDto.setProjectId(1);
		taskDto.setProject(projectDto);

		UserDto userDto = new UserDto();
		userDto.setUserId(1);
		taskDto.setUser(userDto);

		Task taskRecord = Mockito.mock(Task.class);
		when(taskRepository.findById(1)).thenReturn(Optional.of(taskRecord));
		Project projectRecord = Mockito.mock(Project.class);
		when(projectRepository.findById(1)).thenReturn(Optional.of(projectRecord));
		User userRecord = Mockito.mock(User.class);
		when(userRepository.findById(1)).thenReturn(Optional.of(userRecord));
		Task savedTask = Mockito.mock(Task.class);
		when(taskRepository.save(any(Task.class))).thenReturn(savedTask);
		TaskDto savedTaskDto = Mockito.mock(TaskDto.class);
		when(taskTransform.apply(savedTask)).thenReturn(savedTaskDto);
		TaskDto result = testObj.update(taskDto, 1);

		assertThat(result, IsEqual.equalTo(savedTaskDto));
	}

	@Test(expected = ValidationException.class)
	public void testUpdate_whenNoProject() {
		TaskDto taskDto = new TaskDto();
		ProjectDto projectDto = new ProjectDto();
		projectDto.setProjectId(1);
		taskDto.setProject(projectDto);

		UserDto userDto = new UserDto();
		userDto.setUserId(1);
		taskDto.setUser(userDto);

		when(taskRepository.findById(1)).thenReturn(Optional.ofNullable(null));

		testObj.update(taskDto, 1);

	}

	@Test
	public void testDeleteTaskById() {
		testObj.deleteTaskById(1);
	}

	@Test
	public void testGetParentTasks() {
		Task task = new Task();
		task.setTaskId(1);
		
		TaskDto mockTaskDto = Mockito.mock(TaskDto.class);
		when(taskRepository.findAll()).thenReturn(Arrays.asList(task));
		when(taskTransform.apply(any(Task.class))).thenReturn(mockTaskDto);
		
		List<TaskDto> result = testObj.getParentTasks();
		assertThat(result, IsNull.notNullValue());
		
		Task parentTask = new Task();
		task.setParentTask(parentTask);
		result = testObj.getParentTasks();
		assertThat(result, IsNull.notNullValue());
	}

}
