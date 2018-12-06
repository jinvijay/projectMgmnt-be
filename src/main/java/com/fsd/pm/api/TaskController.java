package com.fsd.pm.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.fsd.pm.service.TaskService;
import com.fsd.pm.service.dto.TaskDto;

@RestController
@RequestMapping(value = { "/task" })
public class TaskController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	TaskService taskService;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TaskDto> getTaskById(@PathVariable("id") int id) {
		LOGGER.info("Fetching Task with id " + id);
		TaskDto task = taskService.findById(id);
		if (task == null) {
			return new ResponseEntity<TaskDto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TaskDto>(task, HttpStatus.OK);
	}

	@PostMapping(value = "/create", headers = "Accept=application/json")
	public ResponseEntity<Void> createTask(@RequestBody TaskDto task, UriComponentsBuilder ucBuilder) {
		LOGGER.info("Creating Task " + task.getTask());
		taskService.createTask(task);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/task/{id}").buildAndExpand(task.getTaskId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@GetMapping(value = "/get", headers = "Accept=application/json")
	public List<TaskDto> getAllTask() {
		List<TaskDto> tasks = taskService.getTasks();
		return tasks;

	}
	
	@GetMapping(value = "/get/parent", headers = "Accept=application/json")
	public List<TaskDto> getAllParentTask() {
		List<TaskDto> tasks = taskService.getParentTasks();
		return tasks;

	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateTask(@RequestBody TaskDto currentTask) {
		TaskDto task = taskService.findById(currentTask.getTaskId());
		if (task == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		taskService.update(currentTask, currentTask.getTaskId());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity<TaskDto> deleteTask(@PathVariable("id") int id) {
		TaskDto task = taskService.findById(id);
		if (task == null) {
			return new ResponseEntity<TaskDto>(HttpStatus.NOT_FOUND);
		}
		taskService.deleteTaskById(id);
		return new ResponseEntity<TaskDto>(HttpStatus.NO_CONTENT);
	}

}
