package com.fsd.pm.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "fsd_pm", name = "users")
public class UserDao {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	@Column(name="first_nm")
	private String firstName;
	
	@Column(name="last_nm")
	private String lastName;
	
	@Column(name="emp_id")
	private int empId;
	
	@Column(name="project_id")
	private int projectId;
	
	@Column(name="task_id")
	private int taskId;

	
}
