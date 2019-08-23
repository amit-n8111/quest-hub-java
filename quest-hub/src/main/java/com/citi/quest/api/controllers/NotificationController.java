package com.citi.quest.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citi.quest.api.dtos.ActiveNotification;
import com.citi.quest.api.service.NotificationService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "API responsible for getting notifications")
@RequestMapping("/api/v1")
public class NotificationController {
	
	@Autowired
	NotificationService notificationService;
	
	@GetMapping(value = "notifications/{userId}")
	public ActiveNotification getActiveNotifications(@PathVariable(value = "userId") String smUser) {
		ActiveNotification notifications = notificationService.getAllActiveNotifications(smUser);
		return notifications;
	}
}
