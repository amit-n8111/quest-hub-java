package com.citi.quest.api.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.citi.quest.api.domain.Questions;

import io.swagger.annotations.ApiOperation;

@RepositoryRestResource
public interface QuestionsRepository extends MongoRepository<Questions, String>{

	@ApiOperation("Find questions for the application.")
	List<Questions> findAll();
	
	
}
