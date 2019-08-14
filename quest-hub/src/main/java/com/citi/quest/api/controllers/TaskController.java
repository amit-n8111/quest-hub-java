package com.citi.quest.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citi.quest.api.dtos.TaskDTO;
import com.citi.quest.api.service.impl.TaskService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "API responsible for Task related operations")
@RequestMapping("/api/v1/task")
public class TaskController {

	@Autowired
	TaskService taskPostService;

	@PostMapping(value = "edit/{taskId}")
	public ResponseEntity<Boolean> saveTask(@RequestBody TaskDTO task, @PathVariable(value = "taskId") String user) {
		taskPostService.postTask(task, user);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
}
