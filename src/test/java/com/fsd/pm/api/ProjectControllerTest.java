package com.fsd.pm.api;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.fsd.pm.service.ProjectService;
import com.fsd.pm.service.dto.ProjectDto;

public class ProjectControllerTest {

	@Mock
	ProjectService projectService;

	@InjectMocks
	private ProjectController testObj;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	public ProjectControllerTest() {

	}

	public ProjectControllerTest(String str) {
		this();

	}

	@Test
	public void testGetProjectById() {

		ProjectDto mockProjectDto = mock(ProjectDto.class);
		when(projectService.findById(1)).thenReturn(mockProjectDto);
		ResponseEntity<ProjectDto> response = testObj.getProjectById(1);
		assertThat(response, IsNull.notNullValue());

		when(projectService.findById(1)).thenReturn(null);
		response = testObj.getProjectById(1);
		assertThat(response, IsNull.notNullValue());
	}

	@Test
	public void testCreateProject() {
		ProjectDto projectDto = new ProjectDto();
		UriComponentsBuilder ucBuilder = UriComponentsBuilder.newInstance();

		ResponseEntity<Void> response = testObj.createProject(projectDto, ucBuilder);
		assertThat(response, IsNull.notNullValue());
	}

	@Test
	public void testGetAllProject() {
		List<ProjectDto> projects = new ArrayList<>();
		when(projectService.getProjects()).thenReturn(projects);

		List<ProjectDto> response = testObj.getAllProject();
		assertThat(response, IsNull.notNullValue());
	}

	@Test
	public void testUpdateProject() {
		ProjectDto projectDto = new ProjectDto();
		projectDto.setProjectId(1);
		when(projectService.findById(1)).thenReturn(projectDto);
		ResponseEntity<String> response = testObj.updateProject(projectDto);

		assertThat(response, IsNull.notNullValue());
	}

	@Test
	public void testDeleteProject() {
		ProjectDto projectDto = new ProjectDto();
		when(projectService.findById(1)).thenReturn(projectDto);
		ResponseEntity<ProjectDto> response = testObj.deleteProject(1);

		assertThat(response, IsNull.notNullValue());
	}

}
