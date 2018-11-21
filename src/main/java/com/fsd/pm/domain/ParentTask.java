package com.fsd.pm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "fsd_pm", name = "parent_task")
public class ParentTask {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "parent_id")
	private int parentId;

	@OneToOne
	@JoinColumn(name = "parent_task")
	private Task parentTask;

}
