package com.fsd.pm.repo;

import org.springframework.data.repository.CrudRepository;

import com.fsd.pm.domain.Project;

public interface ProjectRepository extends CrudRepository<Project, Integer> {

}
