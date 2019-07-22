package com.citi.quest.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citi.quest.api.dtos.DashboardDTO;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "API responsible for loading dashboard")
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

	@GetMapping
	public DashboardDTO getDashboardInfo(String user) {
		return new DashboardDTO();
	}
}
