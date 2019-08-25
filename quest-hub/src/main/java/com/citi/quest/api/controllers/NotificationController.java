package com.citi.quest.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.citi.quest.api.dtos.ActiveNotification;
import com.citi.quest.api.dtos.NotificationDTO;
import com.citi.quest.api.service.NotificationService;
import io.swagger.annotations.Api;

@RestController
@Api(tags = "API responsible for getting notifications")
@RequestMapping("/api/v1")
public class NotificationController {

	@Autowired
	NotificationService notificationService;

	@GetMapping(value = "notifications/activeNotifications")
	public ActiveNotification getActiveNotifications(@RequestHeader(value = "sm_user") String smUser) {
		ActiveNotification notifications = notificationService.getAllActiveNotifications(smUser);
		return notifications;
	}

	@GetMapping(value = "notifications/allNotifications")
	public List<NotificationDTO> getAllNotifications(@RequestHeader(value = "sm_user") String smUser) {
		List<NotificationDTO> notifications = notificationService.getAllNotifications(smUser);
		return notifications;
	}
}
