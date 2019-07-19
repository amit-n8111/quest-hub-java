package com.citi.quest.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.citi.quest.api.domain.Task;

@RepositoryRestResource
public interface TaskRepository extends MongoRepository<Task, String>{

	
	
}
