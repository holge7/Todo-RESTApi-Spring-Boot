package com.todo.api.DTO;

public class TaskDTO {

	private Long id;
	private String name;
	private String description;
	private Boolean finish;

	public TaskDTO() {}

	public TaskDTO(Long id,String name, String description, Boolean finish) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.finish = finish;
	}

	public Long getId() {
		return id;
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

	public Boolean getFinish() {
		return finish;
	}

	public void setFinish(Boolean finish) {
		this.finish = finish;
	}

}
