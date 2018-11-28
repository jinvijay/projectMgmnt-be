package com.fsd.pm.api;

import java.util.List;

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

import com.fsd.pm.domain.Project;
import com.fsd.pm.service.ProjectService;

@RestController
@RequestMapping(value={"/project"})
public class ProjectController {

	@Autowired
	ProjectService projectService;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Project> getProjectById(@PathVariable("id") int id) {
		System.out.println("Fetching Project with id " + id);
		Project project = projectService.findById(id);
		if (project == null) {
			return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}

	@PostMapping(value = "/create", headers = "Accept=application/json")
	public ResponseEntity<Void> createProject(@RequestBody Project project, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Project " + project.getProject());
		projectService.createProject(project);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/project/{id}").buildAndExpand(project.getProjectId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@GetMapping(value = "/get", headers = "Accept=application/json")
	public List<Project> getAllProject() {
		List<Project> projects = projectService.getProjects();
		return projects;

	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateProject(@RequestBody Project currentProject) {
		System.out.println("sd");
		Project project = projectService.findById(currentProject.getProjectId());
		if (project == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		projectService.update(currentProject, currentProject.getProjectId());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity<Project> deleteProject(@PathVariable("id") int id) {
		Project project = projectService.findById(id);
		if (project == null) {
			return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
		}
		projectService.deleteProjectById(id);
		return new ResponseEntity<Project>(HttpStatus.NO_CONTENT);
	}

}
