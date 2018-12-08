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
import com.fsd.pm.domain.Task;
import com.fsd.pm.domain.User;
import com.fsd.pm.repo.ProjectRepository;
import com.fsd.pm.repo.TaskRepository;
import com.fsd.pm.repo.UserRepository;
import com.fsd.pm.service.dto.TaskDto;
import com.fsd.pm.service.transform.TaskDtoTransform;
import com.fsd.pm.service.transform.TaskTransform;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskTransform taskTransform;

	@Autowired
	private TaskDtoTransform taskDtoTransform;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public TaskDto createTask(TaskDto task) {
		
		if(task.getStartDate() == null) {
			task.setStartDate(new Date());
		}
		if(task.getEndDate() == null) {
			Calendar cal = Calendar.getInstance();
	        cal.setTime(task.getStartDate());
	        cal.add(Calendar.DATE, 1); 
	        task.setEndDate(cal.getTime());
		}

		Project foundProject = projectRepository.findById(task.getProject().getProjectId()).orElse(null);
		User foundUser = userRepository.findById(task.getUser().getUserId()).orElse(null);

		Task newTask = taskDtoTransform.apply(task);
		Task savedTask = taskRepository.save(newTask);

		savedTask.setProject(foundProject);
		savedTask.setUser(foundUser);

		return taskTransform.apply(taskRepository.save(savedTask));

	}

	@Override
	public List<TaskDto> getTasks() {
		return StreamSupport.stream(taskRepository.findAll().spliterator(), false).map(taskTransform)
				.collect(Collectors.toList());
	}

	@Override
	public List<TaskDto> getParentTasks() {
		return StreamSupport.stream(taskRepository.findAll().spliterator(), false).map(taskTransform)
				.filter(taskDto -> taskDto.getParentTask() == null).collect(Collectors.toList());
	}

	@Override
	public TaskDto findById(int id) {
		return taskTransform.apply(taskRepository.findById(id).orElse(null));
	}

	@Override
	public TaskDto update(TaskDto task, int id) {

		Task taskRecord = taskRepository.findById(id).orElse(null);
		if (taskRecord == null) {
			throw new ValidationException("Unable to find task with id " + id);
		}

		Project projectRecord = projectRepository.findById(task.getProject().getProjectId()).orElse(null);
		User userRecord = userRepository.findById(task.getUser().getUserId()).orElse(null);

		taskRecord.setEndDate(task.getEndDate());
		taskRecord.setPriority(task.getPriority());
		taskRecord.setTask(task.getTask());
		taskRecord.setStartDate(task.getStartDate());
		taskRecord.setStatus(task.getStatus());
		taskRecord.setProject(projectRecord);
		taskRecord.setUser(userRecord);

		return taskTransform.apply(taskRepository.save(taskRecord));
	}

	@Override
	public void deleteTaskById(int id) {
		taskRepository.deleteById(id);

	}

}
