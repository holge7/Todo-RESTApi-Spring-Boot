package com.todo.api.DTO;

import java.util.List;

public class TaskDTOPageableResponse {

	private int currentPage;
	private int sizePage;
	private List<TaskDTO> dataTask;
	private int totalPages;
	private boolean finalPage;

	public TaskDTOPageableResponse() {
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getSizePage() {
		return sizePage;
	}

	public void setSizePage(int sizePage) {
		this.sizePage = sizePage;
	}

	public List<TaskDTO> getDataTask() {
		return dataTask;
	}

	public void setDataTask(List<TaskDTO> dataTask) {
		this.dataTask = dataTask;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isFinalPage() {
		return finalPage;
	}

	public void setFinalPage(boolean finalPage) {
		this.finalPage = finalPage;
	}

}
