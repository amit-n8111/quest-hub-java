package com.citi.quest.api.controllers;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citi.quest.api.domain.Task;
import com.citi.quest.api.dtos.ApplicationDTO;
import com.citi.quest.api.dtos.SearchTaskDTO;
import com.citi.quest.api.dtos.TaskDTO;
import com.citi.quest.api.dtos.TaskResponseDTO;
import com.citi.quest.api.service.impl.TaskServiceImpl;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "questHub-Tasks APIs")
@RequestMapping("/api/v1")
public class TaskController {

	@Autowired
	TaskServiceImpl taskPostService;

	@PostMapping(value = "task/edit/")
	public ResponseEntity<Boolean> saveTask(@RequestHeader(value = "sm_user") String user, @RequestBody TaskDTO task) {
		taskPostService.postTask(task, user);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@GetMapping(value = "task/tasks")
	public List<Task> getTasks() {
		return taskPostService.getTasks();
	}

	@PostMapping(value = "task/tasks")
	public List<TaskResponseDTO> searchTasks(@RequestBody SearchTaskDTO searchTaskDTO,
			@RequestHeader(value = "sm_user") String user) {

		if (StringUtils.isNotBlank(searchTaskDTO.getSearch()) || StringUtils.isNotBlank(searchTaskDTO.getSkillId())
				|| (searchTaskDTO.getTasktopicId() != null && searchTaskDTO.getTasktopicId() > 0)
				|| (searchTaskDTO.getTasktypeId() != null && searchTaskDTO.getTasktypeId() > 0) 
				|| StringUtils.isNotBlank(searchTaskDTO.getCreatedBy()))
				 {
			return taskPostService.searchTasks(searchTaskDTO, user);
		}
		else {
			System.out.println("getting recommended tasks");
			return taskPostService.getRecomendedTasks(user,searchTaskDTO.getPageNumber(),searchTaskDTO.getPageSize());
		}
	}

	@PostMapping(value = "task/apply/{taskId}")
	public Boolean applyTask(@PathVariable(value = "taskId") Long taskId, @RequestBody ApplicationDTO applicationDTO,
			@RequestHeader(value = "sm_user") String user) {
		return taskPostService.applyTask(user, taskId, applicationDTO);
	}

	@PostMapping(value = "tasks")
	public Task saveTask(@RequestHeader(value = "sm_user") String user, @RequestBody Task task) {
		return taskPostService.saveTask(user, task);
	}

	@GetMapping(value = "tasks/{taskId}")
	public TaskResponseDTO getTask(@RequestHeader(value = "sm_user") String user,
			@PathVariable(value = "taskId") Long taskId) {
		return taskPostService.getTask(taskId, user);
	}
}
