package com.citi.quest.api.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.citi.quest.api.domain.Task;
import com.citi.quest.api.domain.Topic;

import io.swagger.annotations.ApiOperation;

@RepositoryRestResource
public interface TaskRepository extends MongoRepository<Task, String>{

	/*
	 * @ApiOperation("Find tasks in draft status") List<Task> findAll();
	 */
	
}
