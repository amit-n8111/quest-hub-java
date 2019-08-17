package com.citi.quest.api.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citi.quest.api.domain.Application;
import com.citi.quest.api.domain.Task;
import com.citi.quest.api.domain.UserInfo;
import com.citi.quest.api.dtos.ApplicationDTO;
import com.citi.quest.api.dtos.SearchTaskDTO;
import com.citi.quest.api.dtos.TaskDTO;
import com.citi.quest.api.event.handlers.ApplicationEventHandler;
import com.citi.quest.api.repositories.ApplicationRepository;
import com.citi.quest.api.repositories.TaskRepository;
import com.citi.quest.api.repositories.UserInfoRepository;

@Service
@Transactional
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	ApplicationRepository applicationRepository;
	
	@Autowired
	UserInfoRepository userRepository;
	
	@Autowired
	MongoOperations mongoOperations;

	@Autowired
	ApplicationEventHandler applicationHandler;
	
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

		task.setTaskId(taskDto.getTaskId());
		task.setTaskName(taskDto.getTaskName());
		task.setTaskStatusId(taskDto.getTaskStatusId());
		task.setTaskDescription(taskDto.getTaskDescription());
		task.setTaskType(taskDto.getTaskType());
		task.setTaskDueDate(taskDto.getTaskDueDate());

		task.setScreeningQuestions(taskDto.getScreeningQuestions());
		task.setSkills(taskDto.getSkills());

		task.setTopicId(taskDto.getTaskTopicId());

		task.setTaskCreatedBy(taskDto.getTaskCreatedBy());
		task.setTaskCreateDate(new Date());

		task.setTaskStatusId(1L);

		return task;
	}

	public List<Task> getTasks() {
		return taskRepository.findAll();
	}

	public List<Task> searchTasks(SearchTaskDTO searchTaskDTO) {

		Criteria criteria = new Criteria();
		Query query = new Query();

		if (StringUtils.isNotBlank(searchTaskDTO.getCreatedBy())) {
			query.addCriteria(Criteria.where("taskCreatedBy").is(searchTaskDTO.getCreatedBy()));
		}

		if (StringUtils.isNotBlank(searchTaskDTO.getSearch())) {
			query.addCriteria(new Criteria().orOperator(criteria.where("taskName").regex(searchTaskDTO.getSearch()),
					criteria.where("taskDescription").regex(searchTaskDTO.getSearch())));

		}

		query.with(new PageRequest(searchTaskDTO.getPageNumber() - 1, searchTaskDTO.getPageSize()));

		if (StringUtils.isNotBlank(searchTaskDTO.getSortBy())) {

			if ("ASC".equalsIgnoreCase(searchTaskDTO.getSortOrder())) {
				query.with(new Sort(Sort.Direction.ASC, searchTaskDTO.getSortBy()));
			} else {
				query.with(new Sort(Sort.Direction.DESC, searchTaskDTO.getSortBy()));
			}

		}
		return mongoOperations.find(query, Task.class);
	}

	public String applyTask(String user, String taskId, ApplicationDTO applicationDTO) {
		Application application = new Application();
		UserInfo userInfo = userRepository.findBySoeId(user);
		application.setUser(userInfo);
		application.setCommentsOrNotes(applicationDTO.getCommentsOrNotes());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEEE MMMMM yyyy HH:mm:ss.SSSZ");
		LocalDateTime startdateTime = LocalDateTime.parse(applicationDTO.getAvailableDateRange().get(0), formatter);
		LocalDateTime enddateTime = LocalDateTime.parse(applicationDTO.getAvailableDateRange().get(1), formatter);
		List<LocalDateTime> times = new ArrayList<LocalDateTime>();
		times.add(startdateTime);
		times.add(enddateTime);
		application.setAvailableDateRange(times);
		application.setTask(taskRepository.findOne(Long.parseLong(taskId)));
		applicationHandler.handleApplicationCreateWithoutId(application);
		return "Task Application Sent";
	}

}
