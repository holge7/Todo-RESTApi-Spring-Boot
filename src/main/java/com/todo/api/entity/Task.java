package com.todo.api.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long task_id;
	private String name;
	private String description;
	private int process;
	private Boolean close;
	
	@OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<TaskLog> logs = new HashSet<>();
	
	public Task() {}
	
	public Task(long id, String name, String description, int process, Boolean close) {
		this.task_id = id;
		this.name = name;
		this.description = description;
		this.process = process;
		this.close = close;
	}

	public long getTask_id() {
		return task_id;
	}

	public void setTask_id(long id) {
		this.task_id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProcess() {
		return process;
	}

	public void setProcess(int process) {
		this.process = process;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getClose() {
		return close;
	}

	public void setClose(Boolean close) {
		this.close = close;
	}

}
