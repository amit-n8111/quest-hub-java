package com.citi.quest.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citi.quest.api.domain.Favorite;
import com.citi.quest.api.domain.UserInfo;
import com.citi.quest.api.repositories.FavoriteRepository;
import com.citi.quest.api.repositories.UserInfoRepository;
import com.citi.quest.api.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	FavoriteRepository favRepository;
	
	@Autowired
	UserInfoRepository userInfoRepository;
	
	@Override
	public Boolean markTaskAsFavorite(long taskId, String user) {
		Favorite favorite = new Favorite();
		favorite.setTaskIds(taskId);
		favorite.setUserId(user);
		favRepository.save(favorite);
		return true;
	}

	@Override
	public UserInfo getUserInfo(String userId) {
		
		return userInfoRepository.findBySoeId(userId);
	}

}
