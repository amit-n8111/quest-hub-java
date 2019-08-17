package com.citi.quest.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.citi.quest.api.domain.Application;

import io.swagger.annotations.Api;

@Api(tags = "questHub-Application APIs")
@RepositoryRestResource
public interface ApplicationRepository extends MongoRepository<Application, Long>{

}
