package com.todo.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.todo.api.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
		
	/**
	 * Native query to filter tasks by finish status 1
	 * @return List(Task)
	 */
	@Query(value = "SELECT * FROM task WHERE finish = 1", nativeQuery = true)
	public List<Task> endTasks();
	
	/**
	 * Native query to filter tasks by finish status 0
	 * @return List(Task)
	 */
	@Query(value = "SELECT * FROM task WHERE finish = 0", nativeQuery = true)
	public List<Task> activeTasks();
	
	/**
	 * Native query to update status of one task by her id
	 * @param status (true -> end task / false -> active task)
	 * @param id
	 */
	@Modifying
	@Query(value = "UPDATE task SET finish = :status WHERE id = :id", nativeQuery = true)
	public void changeStatus(@Param("status") boolean status,
							@Param("id") long id);

}
