package com.fsd.pm.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fsd.pm.service.ProjectService;
import com.fsd.pm.service.dto.ProjectDto;

@RestController
@RequestMapping(value = { "/project" })
public class ProjectController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

	@Autowired
	ProjectService projectService;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProjectDto> getProjectById(@PathVariable("id") int id) {
		LOGGER.info("Fetching Project with id " + id);
		ProjectDto project = projectService.findById(id);
		if (project == null) {
			return new ResponseEntity<ProjectDto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProjectDto>(project, HttpStatus.OK);
	}

	@PostMapping(value = "/create", headers = "Accept=application/json")
	public ResponseEntity<Void> createProject(@RequestBody ProjectDto project, UriComponentsBuilder ucBuilder) {
		LOGGER.info("Creating Project " + project.getProject());
		ProjectDto newProject = projectService.createProject(project);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/project/{id}").buildAndExpand(newProject.getProjectId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@GetMapping(value = "/get", headers = "Accept=application/json")
	public List<ProjectDto> getAllProject() {
		List<ProjectDto> projects = projectService.getProjects();
		return projects;

	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateProject(@RequestBody ProjectDto currentProject) {
		ProjectDto project = projectService.findById(currentProject.getProjectId());
		if (project == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		projectService.update(currentProject, currentProject.getProjectId());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity<ProjectDto> deleteProject(@PathVariable("id") int id) {
		ProjectDto project = projectService.findById(id);
		if (project == null) {
			return new ResponseEntity<ProjectDto>(HttpStatus.NOT_FOUND);
		}
		projectService.deleteProjectById(id);
		return new ResponseEntity<ProjectDto>(HttpStatus.NO_CONTENT);
	}

}
