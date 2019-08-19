package com.citi.quest.api.service;

import java.util.List;

import com.citi.quest.api.domain.UserInfo;
import com.citi.quest.api.dtos.SearchUserDTO;

public interface UserService {

	Boolean markTaskAsFavorite(long parseLong, String user);
	
	UserInfo getUserInfo(String userId);

	List<UserInfo> getAllUserInfo(SearchUserDTO searchUserDTO);

}
