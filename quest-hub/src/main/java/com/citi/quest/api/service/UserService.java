package com.citi.quest.api.service;

import java.util.List;

import com.citi.quest.api.domain.UserInfo;
import com.citi.quest.api.dtos.SearchUserDTO;
import com.citi.quest.api.dtos.SearchUserResponseDTO;

public interface UserService {

	Boolean markTaskAsFavorite(long parseLong, String user);
	
	UserInfo getUserInfo(String userId);

	List<SearchUserResponseDTO> searchUserInfo(SearchUserDTO searchUserDTO);

	List<SearchUserResponseDTO> getRecomendedUsers(String user, Integer pageNumber, Integer pageSize);

}
