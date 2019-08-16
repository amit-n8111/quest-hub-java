package com.citi.quest.api.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.citi.quest.api.domain.Question;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "questHub-Questions APIs")
@RepositoryRestResource
public interface QuestionsRepository extends MongoRepository<Question, Long>{

	@Override
	@ApiOperation("Find questions for the application.")
	List<Question> findAll();
	
}
