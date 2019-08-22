package com.citi.quest.api.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.citi.quest.api.domain.Notification;

import io.swagger.annotations.Api;

@RepositoryRestResource
@Api(tags = "questHub-Notification APIs")
public interface NotificationRepository extends MongoRepository<Notification, Long>{

	List<Notification> findBySoeId(@Param("soeId") String soeId);
}
