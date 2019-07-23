package com.citi.quest.api.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.citi.quest.api.domain.Skill;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "QuestHub APIS for Skills related operations")
@RepositoryRestResource
public interface SkillsRepository extends MongoRepository<Skill, Long> {

	@Override
	@ApiOperation("Find questions for the application.")
	List<Skill> findAll();
}
