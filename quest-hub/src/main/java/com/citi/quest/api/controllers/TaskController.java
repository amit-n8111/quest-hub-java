package com.citi.quest.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citi.quest.api.domain.Task;
import com.citi.quest.api.service.api.TaskPostService;

import io.swagger.annotations.Api;


@RestController
@Api(tags = "API responsible for Task related operations")
@RequestMapping("/api/v1/task")
public class TaskController {

	@Autowired
	TaskPostService taskPostService;
	
	@PostMapping(value= "{user}")
	public Task saveTask(@RequestBody Task task, @PathVariable(value="user") String user){
		task = taskPostService.postTask(task, user);
		return task;
	}
}
