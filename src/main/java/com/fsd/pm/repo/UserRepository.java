package com.fsd.pm.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fsd.pm.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
