package com.citi.quest.api.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citi.quest.api.domain.Task;
import com.citi.quest.api.domain.UserInfo;
import com.citi.quest.api.dtos.TaskDTO;
import com.citi.quest.api.enums.TaskStatus;
import com.citi.quest.api.repositories.TaskRepository;
import com.citi.quest.api.repositories.UserInfoRepository;


@Service
@Transactional
public class TaskService {

	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserInfoRepository userRepository;
	

	public void postTask(TaskDTO taskDto, String user) {
		UserInfo userInfo = userRepository.findBySoeId(user);
		Task task = updateTaskInfo(userInfo, taskDto);
		task = taskRepository.save(task);
	}

	/*
	 * 
	 */
	private Task updateTaskInfo(UserInfo userInfo, TaskDTO taskDto) {
		Task task = new Task();
		
		task.setCreatedBy(userInfo);
		task.setCreatedDate(LocalDateTime.now());
		task.setStatus(TaskStatus.PUBLISHED);
		return task;
	}

	
}
