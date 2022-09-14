package com.todo.api.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TaskLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long logID;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_id", nullable = false)
	private Task task;

	public long getLogID() {
		return logID;
	}

	public void setLogID(long logID) {
		this.logID = logID;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
	
	
	
	
}
