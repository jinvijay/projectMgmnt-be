package com.fsd.pm.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "fsd_pm", name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int userId;

	@Column(name = "first_nm")
	private String firstName;

	@Column(name = "last_nm")
	private String lastName;

	@Column(name = "emp_id")
	private int empId;

	@Column(name = "project_id")
	private int projectId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "task_id")
	private Task task;

}
