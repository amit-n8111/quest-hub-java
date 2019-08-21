package com.citi.quest.api.service.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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

		task.setTaskStatusId(1);

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

	public String applyTask(String user, Long taskId, ApplicationDTO applicationDTO) {
		Application application = new Application();
		UserInfo userInfo = userRepository.findBySoeId(user);
		application.setUser(userInfo);
		application.setCommentsOrNotes(applicationDTO.getCommentsOrNotes());
		application.setStartDate(applicationDTO.getStartDate());
		application.setEndDate(applicationDTO.getEndDate());
		application.setTask(taskRepository.findOne(taskId));
		Long maxId = 0L;
		if (null == application.getId() || application.getId() <= 0) {
			List<Application> applications = applicationRepository.findAll();
			if (CollectionUtils.isNotEmpty(applications)) {
				Application max = applications.stream().max(Comparator.comparing(Application::getId)).get();
				maxId = max.getId();
			}
			application.setId(maxId + 1);
		}
		applicationRepository.save(application);
		return "Task Application Sent";
	}

}
