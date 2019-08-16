package com.citi.quest.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.citi.quest.api.domain.Task;
import com.citi.quest.api.dtos.TaskDTO;

import io.swagger.annotations.Api;

@RepositoryRestResource
@Api(tags = "questHub-Tasks APIs")
public interface TaskRepository extends MongoRepository<Task, Long>{

	/*
	 * @ApiOperation("Find tasks in draft status") List<Task> findAll();
	 */
	TaskDTO findTaskById(Long id);
	
	//List<Task> searchTasks(SearchTaskDTO searchTask){
		
	
	
}
