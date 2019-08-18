package com.citi.quest.api.service;

import com.citi.quest.api.domain.UserInfo;

public interface UserService {

	Boolean markTaskAsFavorite(long parseLong, String user);
	
	UserInfo getUserInfo(String userId);

}
