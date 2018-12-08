package com.fsd.pm.service;

import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import com.fsd.pm.domain.User;
import com.fsd.pm.repo.ProjectRepository;
import com.fsd.pm.repo.UserRepository;
import com.fsd.pm.service.dto.ProjectDto;
import com.fsd.pm.service.dto.UserDto;
import com.fsd.pm.service.transform.ProjectDtoTransform;
import com.fsd.pm.service.transform.ProjectTransform;

public class ProjectServiceImplTest {

	@Mock
	private ProjectDtoTransform projectDtoTransform;

	@Mock
	private ProjectTransform projectTransform;

	@Mock
	private ProjectRepository projectRepository;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private ProjectServiceImpl testObj;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateProject() {

		ProjectDto projectDto = new ProjectDto();
		UserDto manager = new UserDto();
		manager.setEmpId(1);
		projectDto.setManager(manager);

		// Mock
		User mockManager = Mockito.mock(User.class);
		when(userRepository.findById(any(Integer.class))).thenReturn(Optional.of(mockManager));
		Project mockProject = Mockito.mock(Project.class);
		when(projectDtoTransform.apply(any(ProjectDto.class))).thenReturn(mockProject);
		Project mockSavedProject = Mockito.mock(Project.class);
		when(projectRepository.save(any(Project.class))).thenReturn(mockSavedProject);

		ProjectDto mockProjectDto = Mockito.mock(ProjectDto.class);
		when(projectTransform.apply(mockSavedProject)).thenReturn(mockProjectDto);

		ProjectDto result = testObj.createProject(projectDto);
		assertThat(result, IsEqual.equalTo(mockProjectDto));
	}

	@Test
	public void testGetProjects() {
		List<Project> projectsList = new ArrayList<>();
		when(projectRepository.findAll()).thenReturn(projectsList);
		List<ProjectDto> result = testObj.getProjects();

		assertThat(result, IsNull.notNullValue());
	}

	@Test
	public void testFindById() {

		Project mockProject = Mockito.mock(Project.class);
		when(projectRepository.findById(1)).thenReturn(Optional.of(mockProject));

		ProjectDto mockProjectDto = Mockito.mock(ProjectDto.class);
		when(projectTransform.apply(mockProject)).thenReturn(mockProjectDto);
		ProjectDto result = testObj.findById(1);

		assertThat(result, IsEqual.equalTo(mockProjectDto));

		when(projectRepository.findById(1)).thenReturn(Optional.ofNullable(null));
		result = testObj.findById(1);

		assertThat(result, IsEqual.equalTo(null));
	}

	@Test
	public void testUpdate() {
		ProjectDto projectDto = new ProjectDto();
		projectDto.setProjectId(1);
		UserDto manager = new UserDto();
		manager.setEmpId(1);
		projectDto.setManager(manager);

		// Mock
		User mockManager = Mockito.mock(User.class);
		when(userRepository.findById(any(Integer.class))).thenReturn(Optional.of(mockManager));
		Project mockProject = Mockito.mock(Project.class);
		when(projectRepository.findById(1)).thenReturn(Optional.of(mockProject));
		Project mockSavedProject = Mockito.mock(Project.class);
		when(projectRepository.save(any(Project.class))).thenReturn(mockSavedProject);

		ProjectDto mockProjectDto = Mockito.mock(ProjectDto.class);
		when(projectTransform.apply(mockSavedProject)).thenReturn(mockProjectDto);

		ProjectDto result = testObj.update(projectDto, 1);
		assertThat(result, IsEqual.equalTo(mockProjectDto));
	}

	@Test(expected = ValidationException.class)
	public void testUpdate_whenNoProject() {
		ProjectDto projectDto = new ProjectDto();
		projectDto.setProjectId(1);

		// Mock
		User mockManager = Mockito.mock(User.class);
		when(userRepository.findById(any(Integer.class))).thenReturn(Optional.of(mockManager));
		when(projectRepository.findById(1)).thenReturn(Optional.ofNullable(null));

		testObj.update(projectDto, 1);

	}

	@Test
	public void testDeleteProjectById() {
		testObj.deleteProjectById(1);
	}

}
