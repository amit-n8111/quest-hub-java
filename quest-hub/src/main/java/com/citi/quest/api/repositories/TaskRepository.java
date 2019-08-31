package com.citi.quest.api.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.citi.quest.api.domain.Task;

import io.swagger.annotations.Api;

@RepositoryRestResource
@Api(tags = "questHub-Tasks APIs")
public interface TaskRepository extends MongoRepository<Task, Long> {

	/*
	 * @ApiOperation("Find tasks in draft status") List<Task> findAll();
	 */
	Task findByTaskId(Long taskId);

	// List<Task> searchTasks(SearchTaskDTO searchTask){

	@Query("{ 'taskAssignedTo': ?0, 'taskStatusId': ?1}")
	List<Task> findByTaskAssignedToAndTaskStatusId(String taskAssignedTo, Integer taskStatusId);
	
	@Query("{'taskStatusId': ?0}")
	List<Task> findByTaskStatusId(Integer taskStatusId);

}
