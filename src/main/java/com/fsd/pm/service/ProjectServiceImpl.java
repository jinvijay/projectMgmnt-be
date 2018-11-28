package com.fsd.pm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsd.pm.domain.Project;
import com.fsd.pm.domain.User;
import com.fsd.pm.repo.ProjectRepository;
import com.fsd.pm.repo.UserRepository;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Project createProject(Project project) {

		User manager = null;
		if (project.getManager() != null) {
			manager = userRepository.findById(project.getManager().getEmpId()).orElse(null);
		}

		Project newProject = new Project();
		newProject.setEndDate(project.getEndDate());
		newProject.setPriority(project.getPriority());
		newProject.setProject(project.getProject());
		newProject.setStartDate(project.getStartDate());
		Project savedProject = projectRepository.save(project);

		// Save the manager to the created project
		savedProject.setManager(manager);
		return projectRepository.save(project);

	}

	@Override
	public List<Project> getProjects() {
		return (List<Project>) projectRepository.findAll();
	}

	@Override
	public Project findById(int id) {
		return projectRepository.findById(id).orElse(null);
	}

	@Override
	public Project update(Project project, int id) {

		User manager = null;
		if (project.getManager() != null) {
			manager = userRepository.findById(project.getManager().getEmpId()).orElse(null);
		}

		Project projectRecord = findById(id);
		projectRecord.setEndDate(project.getEndDate());
		projectRecord.setPriority(project.getPriority());
		projectRecord.setProject(project.getProject());
		projectRecord.setStartDate(project.getStartDate());
		Project updatedProject = projectRepository.save(projectRecord);

		// Save the manager to the updated project
		updatedProject.setManager(manager);
		return projectRepository.save(updatedProject);
	}

	@Override
	public void deleteProjectById(int id) {
		projectRepository.deleteById(id);

	}

}
