package com.citi.quest.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.citi.quest.api.domain.Notification;

import io.swagger.annotations.Api;

@RepositoryRestResource
@Api(tags = "questHub-Notification APIs")
public interface NotificationRepository extends MongoRepository<Notification, Long> {

}
