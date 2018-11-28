package com.fsd.pm.service;

import java.util.List;

import com.fsd.pm.domain.Project;

public interface ProjectService {

	public Project createProject(Project Project);

	public List<Project> getProjects();

	public Project findById(int id);

	public Project update(Project Project, int id);

	public void deleteProjectById(int id);

}
