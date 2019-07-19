package com.citi.quest.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.citi.quest.api.domain.Skills;

@RepositoryRestResource
public interface SkillsRepository extends MongoRepository<Skills, String>{

	
}
