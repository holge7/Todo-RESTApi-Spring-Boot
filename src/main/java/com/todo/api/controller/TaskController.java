package com.todo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.api.DTO.TaskDTO;
import com.todo.api.DTO.TaskDTOPageableResponse;
import com.todo.api.service.TaskService;
import com.todo.api.util.ApiResponse;
import com.todo.api.util.AppConsts;

@RestController
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/test")
	public String test() {
		return "Tests passed :D";
	}
	
	/**
	 * Get one Task by her id
	 * @param id
	 * @return ResponseEntity
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> findByID(@PathVariable long id){
		TaskDTO task = taskService.findByID(id);
		
		return new ResponseEntity<ApiResponse>(
					new ApiResponse(task),
					HttpStatus.OK
				);
	}
	
	/**
	 * Get all tasks
	 * @return ResponseEntity
	 */
	@GetMapping("/all")
	public ResponseEntity<List<TaskDTO>> getAllTask(){
		return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
	}
	
	/**
	 * Get Tasks in pageable format
	 * @param indexPage
	 * @param sizePage
	 * @param sortDirection
	 * @return ResponseEntity
	 */
	@GetMapping("/pageable")
	public ResponseEntity<ApiResponse> getTasksPagination(
				@RequestParam int indexPage,
				@RequestParam int sizePage,
				@RequestParam(defaultValue = AppConsts.SORT_ASC, required = false) String sortDirection
			){
		TaskDTOPageableResponse data = taskService.getTasksPagination(indexPage, sizePage, sortDirection);
		ApiResponse response = new ApiResponse(data);
		
		return new ResponseEntity<>(
				response,
				HttpStatus.OK
			);
	}
	
	/**
	 * Get tasks filter by status end
	 * @return ResponseEnity
	 */
	@GetMapping("/end")
	public ResponseEntity<ApiResponse> filterStatusEnd() {
		List<TaskDTO> list = taskService.filterStatus(true);
		ApiResponse response = new ApiResponse(list);
		
		return new ResponseEntity<ApiResponse>(
				response,
				HttpStatus.OK
				);
	}
	
	/**
	 * Get tasks filter by status active
	 * @return RepositoryEntity
	 */
	@GetMapping("/active")
	public ResponseEntity<ApiResponse> filterStatusActive() {
		List<TaskDTO> list = taskService.filterStatus(false);
		ApiResponse response = new ApiResponse(list);
		
		return new ResponseEntity<ApiResponse>(
				response,
				HttpStatus.OK
				);
	}
	
	/**
	 * Create new Task
	 * @param newTask
	 * @return ResponseEntity
	 */
	@PostMapping("/create")
	public ResponseEntity<TaskDTO> create(@RequestBody TaskDTO newTask) {
		TaskDTO taskDTO = taskService.create(newTask);
		return new ResponseEntity<>(taskDTO, HttpStatus.CREATED);
	}
	
	/**
	 * Delete task by id
	 * @param id
	 * @return ResponseEntity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable long id){
		TaskDTO taskDTO = taskService.deleteTask(id);
		
		ApiResponse response = new ApiResponse(taskDTO);
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.OK
				);
	}
	
	/**
	 * Finalize task by id
	 * @param id
	 * @return ResponseEnity
	 */
	@PutMapping("/end")
	public ResponseEntity<ApiResponse> endTask(@RequestParam Long id) {
		TaskDTO task = taskService.changeStatus(true, id);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(task), 
				HttpStatus.OK
				);
	}
	
	/**
	 * Reactive task by id
	 * @param id
	 * @return ResponseEntity
	 */
	@PutMapping("/active")
	public ResponseEntity<ApiResponse> activateTask(@RequestParam Long id) {
		TaskDTO task = taskService.changeStatus(false, id);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(task), 
				HttpStatus.OK
				);
	}
	
	
}
