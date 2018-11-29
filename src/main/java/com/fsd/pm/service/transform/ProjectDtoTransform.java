package com.fsd.pm.service.transform;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.fsd.pm.domain.Project;
import com.fsd.pm.service.dto.ProjectDto;

@Component
public class ProjectDtoTransform implements Function<ProjectDto, Project> {

	@Override
	public Project apply(ProjectDto t) {
		if (t == null) {
			return null;
		}
		
		Project newProject = new Project();
		newProject.setProject(t.getProject());
		newProject.setPriority(t.getPriority());
		newProject.setStartDate(t.getStartDate());
		newProject.setEndDate(t.getEndDate());

		return newProject;
	}

}
