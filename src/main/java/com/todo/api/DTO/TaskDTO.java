package com.todo.api.DTO;

public class TaskDTO {

	private Long id;
	private String name;
	private String description;
	private int process;
	private Boolean close;

	public TaskDTO() {}

	public TaskDTO(Long id,String name, String description, int process, Boolean close) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.process = process;
		this.close = close;
	}

	public Long getId() {
		return id;
	}

	public int getProcess() {
		return process;
	}

	public void setProcess(int process) {
		this.process = process;
	}

	public void setId(Long id) {
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

	public Boolean getClose() {
		return close;
	}

	public void setClose(Boolean close) {
		this.close = close;
	}

}
