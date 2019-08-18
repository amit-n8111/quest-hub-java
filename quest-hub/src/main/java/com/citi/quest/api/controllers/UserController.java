package com.citi.quest.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citi.quest.api.domain.UserInfo;
import com.citi.quest.api.service.UserService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "questHub-User APIs")
@RequestMapping("/api/v1/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping(value = "markFavorite/{taskId}")
	public Boolean markAsFavorite( @RequestParam("taskId") Long taskId) {
		String user = "AN58526";
		return userService.markTaskAsFavorite(taskId, user);
	}
	
	@GetMapping(value = "{userId}")
	public UserInfo getUserInfo( @RequestParam("userId") String userId) {
		return userService.getUserInfo(userId);
	}

}
