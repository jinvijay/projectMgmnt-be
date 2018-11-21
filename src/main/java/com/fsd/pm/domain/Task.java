package com.fsd.pm.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "fsd_pm", name = "task")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "task_id")
	private int taskId;

	@JoinColumn(name = "parent_id")
	@OneToOne(cascade = CascadeType.ALL)
	private ParentTask parentTask;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;

	@Column(name = "task")
	private String task;

	@Column(name = "start_dt")
	private Date startDate;

	@Column(name = "end_dt")
	private Date endDate;

	@Column(name = "priority")
	private int priority;

	@Column(name = "status")
	private String status;
}
