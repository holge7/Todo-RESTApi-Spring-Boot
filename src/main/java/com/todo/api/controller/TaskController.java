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
import com.todo.api.exception.ApiNotFoundException;
import com.todo.api.service.TaskService;
import com.todo.api.util.ApiResponse;
import com.todo.api.util.AppConsts;

@RestController
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	
	/*
	 * GET Zone
	 */
	
	@GetMapping("/test")
	public String test() {
		return "Tests passed :D";
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> findByID(@PathVariable long id){
		try {			
			TaskDTO task = taskService.findByID(id);
			
			return new ResponseEntity<ApiResponse>(
					new ApiResponse(task),
					HttpStatus.OK
				);
		} catch (ApiNotFoundException e) {
			return new ResponseEntity<ApiResponse>(
					new ApiResponse(true, e.toString()),
					HttpStatus.NOT_FOUND
				);
		}
		
	}
	

	@GetMapping("/all")
	public ResponseEntity<ApiResponse> getAllTask(){
		List<TaskDTO> tasks = taskService.getAllTasks();
		ApiResponse datApiResponse = new ApiResponse(tasks);
		return new ResponseEntity<>(
						datApiResponse, 
						HttpStatus.OK
					);
	}
	

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
	

	@GetMapping("/end")
	public ResponseEntity<ApiResponse> filterStatusEnd() {
		List<TaskDTO> list = taskService.filterStatus(true);
		ApiResponse response = new ApiResponse(list);
		
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.OK
				);
	}
	

	@GetMapping("/active")
	public ResponseEntity<ApiResponse> filterStatusActive() {
		List<TaskDTO> list = taskService.filterStatus(false);
		ApiResponse response = new ApiResponse(list);
		
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.OK
				);
	}
	

	/*
	 * POST Zone
	 */
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> create(@RequestBody TaskDTO newTask) {
		TaskDTO taskDTO = taskService.create(newTask);
		ApiResponse response = new ApiResponse(taskDTO);
		return new ResponseEntity<>(
					response,
					HttpStatus.CREATED
				);
	}
	
	
	/*
	 * DELETE Zone
	 */

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delete(@PathVariable long id){
		
		try {
			TaskDTO taskDTO = taskService.deleteTask(id);
			
			ApiResponse response = new ApiResponse(taskDTO);
			return new ResponseEntity<ApiResponse>(
						response,
						HttpStatus.OK
					);
		} catch (ApiNotFoundException e) {
			return new ResponseEntity<ApiResponse>(
					new ApiResponse(true, e.toString()),
					HttpStatus.NOT_FOUND
				);
		}
		
	}
	
	@DeleteMapping("/all")
	public ResponseEntity<ApiResponse> deleteAll(){
		List<TaskDTO> tasksDeleted = taskService.deleteAllTasks();
		
		ApiResponse response = new ApiResponse(tasksDeleted);
		return new ResponseEntity<ApiResponse>(
					response,
					HttpStatus.OK
				);
	}
	

	/*
	 *  PUT Zone
	 */
	
	@PutMapping("/end")
	public ResponseEntity<ApiResponse> endTask(@RequestParam Long id) {
		try {
			TaskDTO task = taskService.changeStatus(true, id);
			return new ResponseEntity<ApiResponse>(
						new ApiResponse(task), 
						HttpStatus.OK
					);			
		} catch (ApiNotFoundException e) {
			return new ResponseEntity<ApiResponse>(
					new ApiResponse(true, e.toString()),
					HttpStatus.NOT_FOUND
				);
		}
	}
	

	@PutMapping("/active")
	public ResponseEntity<ApiResponse> activateTask(@RequestParam Long id) {
		try {
			TaskDTO task = taskService.changeStatus(false, id);
			return new ResponseEntity<ApiResponse>(
					new ApiResponse(task), 
					HttpStatus.OK
				);
		} catch (ApiNotFoundException e) {
			return new ResponseEntity<ApiResponse>(
					new ApiResponse(true, e.toString()),
					HttpStatus.NOT_FOUND
				);
		}
	}
	
	@PutMapping("/process")
	public ResponseEntity<ApiResponse> changeProcess(@RequestParam long id, 
													@RequestParam int process)
	{
		
		try {
			TaskDTO task = taskService.changeProcess(id, process);
			return new ResponseEntity<ApiResponse>(
					new ApiResponse(task), 
					HttpStatus.OK
				);
		} catch (ApiNotFoundException e) {
			return new ResponseEntity<ApiResponse>(
					new ApiResponse(true, e.toString()),
					HttpStatus.NOT_FOUND
				);
		}
		
	}
	
	
}
