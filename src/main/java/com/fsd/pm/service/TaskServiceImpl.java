package com.fsd.pm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsd.pm.domain.Task;
import com.fsd.pm.repo.TaskRepository;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Task createTask(Task task) {
		Task savedTask = taskRepository.save(task);
		return savedTask;

	}

	@Override
	public List<Task> getTasks() {
		return (List<Task>) taskRepository.findAll();
	}

	@Override
	public Task findById(int id) {
		return taskRepository.findById(id).orElse(null);
	}

	@Override
	public Task update(Task task, int id) {

		Task taskRecord = findById(id);
		taskRecord.setEndDate(task.getEndDate());
		taskRecord.setPriority(task.getPriority());
		taskRecord.setTask(task.getTask());
		taskRecord.setStartDate(task.getStartDate());
		taskRecord.setStatus(task.getStatus());
		return taskRepository.save(taskRecord);
	}

	@Override
	public void deleteTaskById(int id) {
		taskRepository.deleteById(id);

	}

}
