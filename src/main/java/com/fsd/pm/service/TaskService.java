package com.fsd.pm.service;

import java.util.List;

import com.fsd.pm.domain.Task;

public interface TaskService {

	public Task createTask(Task Task);

	public List<Task> getTasks();

	public Task findById(int id);

	public Task update(Task Task, int id);

	public void deleteTaskById(int id);

}
