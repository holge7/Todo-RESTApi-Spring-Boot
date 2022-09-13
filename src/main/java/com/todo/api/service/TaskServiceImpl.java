package com.todo.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;


import com.todo.api.DTO.TaskDTO;
import com.todo.api.DTO.TaskDTOPageableResponse;
import com.todo.api.entity.Task;
import com.todo.api.exception.ApiException;
import com.todo.api.exception.ApiNotFoundException;
import com.todo.api.repository.TaskRepository;
import com.todo.api.util.AppConsts;

@Service
public class TaskServiceImpl implements TaskService{
	
	private final String RESOURCE_NAME = "Task";

	
	@Autowired
	TaskRepository taskRepository;

	public TaskServiceImpl() {}

	/**
	 * Create one task
	 * @param taskDTO
	 * @return taskDTO
	 */
	@Override
	public TaskDTO create(TaskDTO taskDTO) {
		Task task = mapTaskEntity(taskDTO);
		Task taskSave = taskRepository.save(task);
		return mapTaskDTO(taskSave);
	}
	
	/**
	 * Find task by id
	 * @param id
	 * @throws ApiNotFoundException
	 * @return TaskDTO
	 */
	@Override
	public TaskDTO findByID(long id) {
		Task task = taskRepository.findById(id)
				.orElseThrow(()->new ApiNotFoundException(RESOURCE_NAME, Long.toString(id)));
		
		return mapTaskDTO(task);
	}
	
	/**
	 * Get all tasks
	 * @return List(TaskDTO)
	 */
	@Override
	public List<TaskDTO> getAllTasks() {
		List<Task> tasksList = taskRepository.findAll();
		return tasksList.stream()
				.map(task -> mapTaskDTO(task))
				.toList();
	}
	
	/**
	 * Filter tasks by status
	 * @param boolean -> true(tasks end) / false(pending tasks)
	 * @return List(TaskDTO)
	 */
	public List<TaskDTO> filterStatus(boolean status){
		
		List<Task> filterTasks;
		
		if (status) filterTasks = taskRepository.endTasks();
		else filterTasks = taskRepository.activeTasks();
		
		List<TaskDTO> filterTasksDTO = filterTasks.stream()
				.map(task -> mapTaskDTO(task))
				.toList();
			
		return filterTasksDTO;
	}
	
	/**
	 * Get Tasks in pageable format
	 * @param indexPage
	 * @param sizePage
	 * @param sortDirection
	 * @return TaskDTOPageableResponse
	 */
	@Override
	public TaskDTOPageableResponse getTasksPagination(int indexPage, int sizePage, String sortDirection){

		Sort sort;
		
		if (sortDirection.equalsIgnoreCase(AppConsts.SORT_ASC)) sort = Sort.by(Direction.ASC, AppConsts.SORT_BY_DEFAULT);
		else sort = Sort.by(Direction.DESC, AppConsts.SORT_BY_DEFAULT);
		
		Pageable pageable = PageRequest.of(indexPage, sizePage, sort);
		Page<Task> tasks = taskRepository.findAll(pageable);
		List<Task> taksList = tasks.getContent();
		
		List<TaskDTO> taskListDTO = taksList.stream()
				.map(task -> mapTaskDTO(task))
				.toList();
		
		TaskDTOPageableResponse response = new TaskDTOPageableResponse();
		response.setDataTask(taskListDTO);
		response.setCurrentPage(tasks.getNumber());
		response.setFinalPage(tasks.isLast());
		response.setSizePage(sizePage);
		response.setTotalPages(tasks.getTotalPages());
		
		return response;
	}
	
	/**
	 * Delete one Task by her id
	 * @param id
	 * @return TaskDTO (task deleted)
	 */
	@Override
	public TaskDTO deleteTask(long id) {
		
		Task task = taskRepository.findById(id)
						.orElseThrow(() -> new ApiException(RESOURCE_NAME, Long.toString(id), "Not found"));
		
		taskRepository.deleteById(id);
		
		return mapTaskDTO(task);
	}
	
	/**
	 * Change the status of one Task
	 * @param status 
	 * @param id
	 * @return TaskDTO (Task modify)
	 */
	@Transactional
	@Override
	public TaskDTO changeStatus(boolean status, long id) {
		
		Task task = taskRepository.findById(id)
				.orElseThrow(() -> new ApiException(RESOURCE_NAME, Long.toString(id), "Not found"));
		
		try {

			taskRepository.changeStatus(status, id);

			task = taskRepository.findById(id)
					.orElseThrow(() -> new ApiException(RESOURCE_NAME, Long.toString(id), "Not found"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapTaskDTO(task);
	}
	
	/**
	 * Transform Task into TaskDTO
	 * @param taskEntity
	 * @return taskDTO
	 */
	private TaskDTO mapTaskDTO(Task taskEntity) {
		TaskDTO taskDTO = new TaskDTO();
		
		taskDTO.setId(taskEntity.getId());
		taskDTO.setName(taskEntity.getName());
		taskDTO.setDescription(taskEntity.getDescription());
		taskDTO.setFinish(taskEntity.getFinish());
		
		return taskDTO;
	}
	
	/**
	 * Transform TaskDTO into Task
	 * @param taskDTO
	 * @return Task
	 */
	private Task mapTaskEntity(TaskDTO taskDTO) {
		Task taskEntity = new Task();
		
		taskEntity.setName(taskDTO.getName());
		taskEntity.setDescription(taskDTO.getDescription());
		taskEntity.setFinish(taskDTO.getFinish());
		
		return taskEntity;
	}
	
}
