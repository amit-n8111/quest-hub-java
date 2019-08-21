package com.citi.quest.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public Boolean markAsFavorite(@PathVariable Long taskId) {
		String user = "AN58526";
		return userService.markTaskAsFavorite(taskId, user);
	}

	@GetMapping(value = "user/{userId}")
	public UserInfo getUserInfo(@RequestParam("userId") String userId) {
		return userService.getUserInfo(userId);
	}

	@PostMapping(value = "users")
	public List<SearchUserResponseDTO> searchUserInfo(@RequestBody SearchUserDTO searchUserDTO) {
		return userService.searchUserInfo(searchUserDTO);
	}

	@PostMapping(value = "users/saveFromCollaborate")
	public Boolean saveUserfromCollaborate(@RequestBody List<SearchUserDTO> searchUserDTO) {
		//to be implemented...
		return true;
	}

}
