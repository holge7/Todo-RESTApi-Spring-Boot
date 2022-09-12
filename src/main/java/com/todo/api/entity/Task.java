package com.todo.api.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;
	private Boolean finish;
	
	public Task() {}
	
	public Task(long id, String name, String description, Boolean finish) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.finish = finish;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getFinish() {
		return finish;
	}

	public void setFinish(Boolean finish) {
		this.finish = finish;
	}

}
