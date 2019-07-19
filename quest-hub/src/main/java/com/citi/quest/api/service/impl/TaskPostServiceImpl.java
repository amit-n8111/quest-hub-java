package com.citi.quest.api.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citi.quest.api.domain.Task;
import com.citi.quest.api.domain.UserInfo;
import com.citi.quest.api.enums.TaskStatus;
import com.citi.quest.api.repositories.TaskRepository;
import com.citi.quest.api.repositories.UserInfoRepository;
import com.citi.quest.api.service.api.TaskPostService;

/**
 * 
 * 
 *
 */

@Service
@Transactional
public class TaskPostServiceImpl implements TaskPostService{

	
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	UserInfoRepository userRepository;
	
	@Override
	public Task postTask(Task task, String user) {
		UserInfo userInfo = userRepository.findBySoeId(user);
		updateTaskInfo(userInfo, task);
		task = taskRepository.save(task);
		return task;
	}

	/*
	 * 
	 */
	private void updateTaskInfo(UserInfo userInfo, Task task) {
		task.setCreatedBy(userInfo);
		task.setCreatedDate(LocalDateTime.now());
		task.setStatus(TaskStatus.OPEN);
	}

	
}
