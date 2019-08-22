package com.citi.quest.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citi.quest.api.dtos.DashboardDTO;
import com.citi.quest.api.dtos.EmailDTO;
import com.citi.quest.api.notification.EmailNotification;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "API responsible for loading dashboard")
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

	@Autowired
	EmailNotification emailNotification;
	
	@GetMapping
	public DashboardDTO getDashboardInfo(String user) {
		return new DashboardDTO();
	}
	
	@PostMapping("/sendMail")
	public void sendMail(@RequestBody EmailDTO emailDTO) {
		emailNotification.sendEmail(emailDTO);
	}
}
