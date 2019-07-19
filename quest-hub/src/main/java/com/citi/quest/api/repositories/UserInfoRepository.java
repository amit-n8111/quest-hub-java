package com.citi.quest.api.repositories;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.citi.quest.api.domain.Skills;
import com.citi.quest.api.domain.Task;
import com.citi.quest.api.domain.Topic;
import com.citi.quest.api.domain.UserInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RepositoryRestResource
@Api(tags="questHub")
public interface UserInfoRepository extends MongoRepository<UserInfo, String>{
		
	@ApiOperation("Find user Information by soeId")
	UserInfo findBySoeId(@Param("soeId") 
	@ApiParam(name="soeId", required=false, value="details of user" ,defaultValue="sm82199") String soeId);
	
	@ApiOperation("Get all skills of a user by soeId")
	List<Skills> findSkillsBySoeId(String soeId);
	
	@ApiOperation("Get all topics subscribed by user")
	List<Topic> findTopicBySoeId(String soeId);
	
	@ApiOperation("Get all earlier tasks completed by SoeId")
	List<Task> findEarlierTasksBySoeId(String soeId);
	
}
