package com.citi.quest.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.citi.quest.api.domain.Favorite;

import io.swagger.annotations.Api;

@Api(tags = "questHub-Favorite Task APIs")
@RepositoryRestResource
public interface FavoriteRepository extends MongoRepository<Favorite, String>{

}
