package com.citi.quest.api.repositories;

import java.util.List;

import com.citi.quest.api.domain.Skills;
import com.citi.quest.api.domain.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import io.swagger.annotations.ApiOperation;

@RepositoryRestResource
public interface TopicsRepository extends MongoRepository<Topic, String>{

	@ApiOperation("Find questions for the application.")
	List<Topic> findAll();
	
	@ApiOperation("Find all suggested skills for a topic")
	List<Skills> findSkillsByTopicId(String topicId);
}
