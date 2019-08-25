package com.citi.quest.api.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.citi.quest.api.domain.Application;
import com.citi.quest.api.dtos.ApplicationDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "questHub-Application APIs")
@RepositoryRestResource
public interface ApplicationRepository extends MongoRepository<Application, Long> {

	@ApiOperation("Get Application by its id")
	ApplicationDTO findById(@Param("id") Long id);
	
	List<Application> findByTaskTaskId(@Param("taskId") Long taskId);

	List<Application> findByUserSoeId(@Param("soeId") String soeId);
}
