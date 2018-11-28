package com.fsd.pm.api;

import java.util.List;

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

import com.fsd.pm.domain.Task;
import com.fsd.pm.service.TaskService;

@RestController
@RequestMapping(value = { "/task" })
public class TaskController {

	@Autowired
	TaskService taskService;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Task> getTaskById(@PathVariable("id") int id) {
		System.out.println("Fetching Task with id " + id);
		Task task = taskService.findById(id);
		if (task == null) {
			return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}

	@PostMapping(value = "/create", headers = "Accept=application/json")
	public ResponseEntity<Void> createTask(@RequestBody Task task, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Task " + task.getTask());
		taskService.createTask(task);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/task/{id}").buildAndExpand(task.getTaskId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@GetMapping(value = "/get", headers = "Accept=application/json")
	public List<Task> getAllTask() {
		List<Task> tasks = taskService.getTasks();
		return tasks;

	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateTask(@RequestBody Task currentTask) {
		System.out.println("sd");
		Task task = taskService.findById(currentTask.getTaskId());
		if (task == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		taskService.update(currentTask, currentTask.getTaskId());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity<Task> deleteTask(@PathVariable("id") int id) {
		Task task = taskService.findById(id);
		if (task == null) {
			return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		}
		taskService.deleteTaskById(id);
		return new ResponseEntity<Task>(HttpStatus.NO_CONTENT);
	}

}
