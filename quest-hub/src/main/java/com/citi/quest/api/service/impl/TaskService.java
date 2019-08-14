package com.citi.quest.api.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citi.quest.api.domain.Task;
import com.citi.quest.api.dtos.TaskDTO;
import com.citi.quest.api.repositories.TaskRepository;

@Service
@Transactional
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public void postTask(TaskDTO taskDto, String user) {
		// UserInfo userInfo = userRepository.findBySoeId(taskDto.getTaskCreatedBy());

		Task task = updateTaskInfo(taskDto);

		task = taskRepository.save(task);
	}

	/*
	 * 
	 */
	private Task updateTaskInfo(TaskDTO taskDto) {
		Task task = new Task();

		task.setId(taskDto.getTaskId());
		task.setTaskId(taskDto.getTaskId());
		task.setTaskName(taskDto.getTaskName());
		task.setTaskStatusId(taskDto.getTaskStatusId());
		task.setTaskDescription(taskDto.getTaskDescription());
		task.setTaskType(taskDto.getTaskType());
		task.setTaskDueDate(taskDto.getTaskDueDate());

		task.setScreeningQuestions(taskDto.getScreeningQuestions());
		task.setSkills(taskDto.getSkills());

		task.setTopicId(taskDto.getTopicId());

		task.setTaskCreatedBy(taskDto.getTaskCreatedBy());
		task.setTaskCreateDate(new Date());

		task.setTaskStatusId(1L);

		return task;
	}

}
