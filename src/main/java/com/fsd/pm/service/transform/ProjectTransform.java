package com.fsd.pm.service.transform;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fsd.pm.domain.Project;
import com.fsd.pm.service.dto.ProjectDto;
import com.fsd.pm.service.dto.TaskDto;

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

		if (t.getTasks() != null) {
			newProject.setTasks(t.getTasks().stream().map(task -> {
				TaskDto taskDto = new TaskDto();
				taskDto.setTaskId(task.getTaskId());
				return taskDto;
			}).collect(Collectors.toList()));
		}

		return newProject;
	}

}
