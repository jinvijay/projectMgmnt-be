package com.fsd.pm.service.transform;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fsd.pm.domain.Project;
import com.fsd.pm.service.dto.ProjectDto;

@Component
public class ProjectTransform implements Function<Project, ProjectDto> {

	@Autowired
	private UserTransform userTransform;

	@Override
	public ProjectDto apply(Project t) {

		if (t == null) {
			return null;
		}
		ProjectDto newProject = new ProjectDto();
		newProject.setProjectId(t.getProjectId());
		newProject.setProject(t.getProject());
		newProject.setPriority(t.getPriority());
		newProject.setStartDate(t.getStartDate());
		newProject.setEndDate(t.getEndDate());

		if (t.getManager() != null) {
			newProject.setManager(userTransform.apply(t.getManager()));
		}
		return newProject;
	}

}
