package com.citi.quest.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citi.quest.api.domain.Favorite;
import com.citi.quest.api.repositories.FavoriteRepository;
import com.citi.quest.api.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	FavoriteRepository favRepository;
	
	@Override
	public Boolean markTaskAsFavorite(long taskId, String user) {
		Favorite favorite = new Favorite();
		favorite.setTaskIds(taskId);
		favorite.setUserId(user);
		favRepository.save(favorite);
		return true;
	}

}
