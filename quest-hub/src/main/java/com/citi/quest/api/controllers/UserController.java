package com.citi.quest.api.controllers;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citi.quest.api.domain.UserInfo;
import com.citi.quest.api.dtos.SearchUserDTO;
import com.citi.quest.api.dtos.SearchUserResponseDTO;
import com.citi.quest.api.service.UserService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "questHub-User APIs")
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(value = "user/markFavorite/{taskId}")
	public Boolean markAsFavorite(@PathVariable Long taskId,@RequestHeader(value = "sm_user") String user) {
		return userService.markTaskAsFavorite(taskId, user);
	}

	@GetMapping(value = "user/{userId}")
	public UserInfo getUserInfo(@RequestParam("userId") String userId) {
		return userService.getUserInfo(userId);
	}

	@PostMapping(value = "users")
	public List<SearchUserResponseDTO> searchUserInfo(@RequestBody SearchUserDTO searchUserDTO,
			@RequestHeader(value = "sm_user") String user) {
		
		if (StringUtils.isNotBlank(searchUserDTO.getSearch()) 
				|| (searchUserDTO.getSkillId() != null && searchUserDTO.getSkillId() > 0)
				|| (searchUserDTO.getTaskTopicId() != null && searchUserDTO.getTaskTopicId() > 0)
				|| (searchUserDTO.getTaskId() == null) ) {
			
			return userService.searchUserInfo(searchUserDTO);
		} else {
			System.out.println("getting recommended users");
			return userService.getRecomendedUsers(user,searchUserDTO.getTaskId(), searchUserDTO.getPageNumber(), searchUserDTO.getPageSize());
		}
	}

	@PostMapping(value = "users/saveFromCollaborate")
	public Boolean saveUserfromCollaborate(@RequestBody List<SearchUserDTO> searchUserDTO) {
		//to be implemented...
		return true;
	}

}
