package com.fsd.pm.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsd.pm.domain.Project;
import com.fsd.pm.domain.User;
import com.fsd.pm.repo.ProjectRepository;
import com.fsd.pm.repo.UserRepository;
import com.fsd.pm.service.dto.ProjectDto;
import com.fsd.pm.service.transform.ProjectDtoTransform;
import com.fsd.pm.service.transform.ProjectTransform;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDtoTransform projectDtoTransform;

	@Autowired
	private ProjectTransform projectTransform;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public ProjectDto createProject(ProjectDto project) {

		User manager = null;
		if (project.getManager() != null) {
			manager = userRepository.findById(project.getManager().getUserId()).orElse(null);
		}
		
		if(project.getStartDate() == null) {
			project.setStartDate(new Date());
		}
		if(project.getEndDate() == null) {
			Calendar cal = Calendar.getInstance();
	        cal.setTime(project.getStartDate());
	        cal.add(Calendar.DATE, 1); 
			project.setEndDate(cal.getTime());
		}

		Project savedProject = projectRepository.save(projectDtoTransform.apply(project));
		// Save the manager to the created project
		savedProject.setManager(manager);

		savedProject = projectRepository.save(savedProject);
		return projectTransform.apply(savedProject);

	}

	@Override
	public List<ProjectDto> getProjects() {
		return StreamSupport.stream(projectRepository.findAll().spliterator(), false).map(projectTransform)
				.collect(Collectors.toList());
	}

	@Override
	public ProjectDto findById(int id) {
		Project foundProject = projectRepository.findById(id).orElse(null);
		ProjectDto result = null;
		if (foundProject != null) {
			result = projectTransform.apply(foundProject);
		}
		return result;
	}

	@Override
	public ProjectDto update(ProjectDto project, int id) {

		User manager = null;
		if (project.getManager() != null) {
			manager = userRepository.findById(project.getManager().getUserId()).orElse(null);
		}

		Project projectRecord = projectRepository.findById(id).orElse(null);
		if (projectRecord == null) {
			throw new ValidationException("Unable to find project with id " + id);
		}
		projectRecord.setEndDate(project.getEndDate());
		projectRecord.setPriority(project.getPriority());
		projectRecord.setProject(project.getProject());
		projectRecord.setStartDate(project.getStartDate());
		// Save the manager to the updated project
		projectRecord.setManager(manager);

		Project updatedProject = projectRepository.save(projectRecord);

		return projectTransform.apply(updatedProject);
	}

	@Override
	public void deleteProjectById(int id) {
		projectRepository.deleteById(id);
	}

}
