package com.citi.quest.api.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.citi.quest.api.domain.Skill;
import com.citi.quest.api.domain.Topic;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RepositoryRestResource
@Api(tags = "questHub-Topics APIs")
public interface TopicRepository extends MongoRepository<Topic, Long> {

	@ApiOperation("Find questions for the application.")
	List<Topic> findAll();

	@ApiOperation("Find all suggested skills for a topic")
	List<Skill> findSkillsById(String topicId);

	/*
	 * @ApiOperation("Find topics having search text") List<Topic>
	 * findTopicsLikeTopicName(String topicName);
	 */
	

	@ApiOperation("Find topics having search text")
	List<Topic> findByTopicNameContaining(@Param("topicName") String topicName);

}
