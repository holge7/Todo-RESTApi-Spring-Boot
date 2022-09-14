package com.todo.api.service;

import java.util.List;

import com.todo.api.DTO.TaskDTO;
import com.todo.api.DTO.TaskDTOPageableResponse;

public interface TaskService {
	
	public TaskDTO create(TaskDTO taskDTO);
	
	public TaskDTO findByID(long id);
	
	public List<TaskDTO> getAllTasks();
	
	public TaskDTOPageableResponse getTasksPagination(int indexPage, int sizePage, String sortDirection);
	
	public TaskDTO deleteTask(long id);
	
	public List<TaskDTO> deleteAllTasks();
	
	public TaskDTO changeStatus(boolean status, long id);
	
	public List<TaskDTO> filterStatus(boolean status);
	
	public TaskDTO changeProcess(long id, int process);
}
