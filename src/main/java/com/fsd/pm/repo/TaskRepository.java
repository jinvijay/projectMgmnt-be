package com.fsd.pm.repo;

import org.springframework.data.repository.CrudRepository;

import com.fsd.pm.domain.Task;

public interface TaskRepository extends CrudRepository<Task, Integer> {

}
