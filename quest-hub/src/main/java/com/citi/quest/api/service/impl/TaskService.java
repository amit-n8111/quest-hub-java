package com.citi.quest.api.service.impl;

import java.util.ArrayList;
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
import com.citi.quest.api.dtos.EmailDTO;
import com.citi.quest.api.dtos.SearchTaskDTO;
import com.citi.quest.api.dtos.TaskDTO;
import com.citi.quest.api.dtos.TaskResponseDTO;
import com.citi.quest.api.enums.RewardType;
import com.citi.quest.api.enums.TaskStatus;
import com.citi.quest.api.notification.EmailNotification;
import com.citi.quest.api.repositories.ApplicationRepository;
import com.citi.quest.api.repositories.TaskRepository;
import com.citi.quest.api.repositories.TopicRepository;
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
	EmailNotification emailNotofication;

	@Autowired
	TopicRepository topicRepository;

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

	public List<TaskResponseDTO> searchTasks(SearchTaskDTO searchTaskDTO) {

		Criteria criteria = new Criteria();
		Query query = new Query();

		if (StringUtils.isNotBlank(searchTaskDTO.getCreatedBy())) {
			query.addCriteria(Criteria.where("taskCreatedBy").is(searchTaskDTO.getCreatedBy()));
		}

		if (StringUtils.isNotBlank(searchTaskDTO.getSearch())) {
			query.addCriteria(new Criteria().orOperator(criteria.where("taskName").regex(searchTaskDTO.getSearch()),
					criteria.where("taskDescription").regex(searchTaskDTO.getSearch()),
					Criteria.where("skills").elemMatch(Criteria.where("name").regex(searchTaskDTO.getSearch()))));

		}

		query.with(new PageRequest(searchTaskDTO.getPageNumber() - 1, searchTaskDTO.getPageSize()));

		if (StringUtils.isNotBlank(searchTaskDTO.getSortBy())) {

			if ("ASC".equalsIgnoreCase(searchTaskDTO.getSortOrder())) {
				query.with(new Sort(Sort.Direction.ASC, searchTaskDTO.getSortBy()));
			} else {
				query.with(new Sort(Sort.Direction.DESC, searchTaskDTO.getSortBy()));
			}

		}
		List<Task> tasks = mongoOperations.find(query, Task.class);
		return mapToTaskResponseDTO(tasks);
	}

	private List<TaskResponseDTO> mapToTaskResponseDTO(List<Task> tasks) {
		List<TaskResponseDTO> taskDTOs = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(tasks)) {
			for (Task task : tasks) {
				TaskResponseDTO taskDTO = new TaskResponseDTO();
				taskDTO.setTaskCreatedBy(userRepository.findBySoeId(task.getTaskCreatedBy()));
				taskDTO.setManHoursNeeded(task.getManHoursNeeded());
				taskDTO.setMarkedAsFavorite(true);// TO DO
				taskDTO.setNumberOfApplicationRecieved(1);// TO DO
				taskDTO.setRewardTypeId(task.getRewardTypeId());
				for (RewardType rewardType : RewardType.values()) {
					if (rewardType.getId() == task.getRewardTypeId()) {
						taskDTO.setRewardType(rewardType.getRewardType());
					}
				}
				taskDTO.setScreeningQuestions(task.getScreeningQuestions());
				taskDTO.setTaskCreatedDate(task.getTaskCreateDate());
				taskDTO.setTaskDescription(task.getTaskDescription());
				taskDTO.setTaskDueDate(task.getTaskDueDate());
				taskDTO.setTaskId(task.getTaskId());
				taskDTO.setTaskName(task.getTaskName());
				taskDTO.setTaskSkills(task.getSkills());
				taskDTO.setTaskStatusId(task.getTaskStatusId());
				for (TaskStatus status : TaskStatus.values()) {
					if (status.getId() == task.getTaskStatusId()) {
						taskDTO.setTaskStatusName(status.getStatus());
					}
				}
				taskDTO.setTaskTopicId(task.getTaskTopicId());
				if (null != topicRepository.findOne(task.getTaskTopicId())) {
					taskDTO.setTaskTopicName(topicRepository.findOne(task.getTaskTopicId()).getTopicName());
				}

				taskDTO.setTotalTasks(100);// TO DO
				if (null != task.getTaskType()) {
					taskDTO.setTaskType(task.getTaskTypeId().toString());
					taskDTO.setTaskTypeName(task.getTaskType());
				}

				taskDTOs.add(taskDTO);

			}
		}

		return taskDTOs;
	}

	public Boolean applyTask(String user, Long taskId, ApplicationDTO applicationDTO) {
		Application application = new Application();
		UserInfo userInfo = userRepository.findBySoeId(user);
		application.setUser(userInfo);
		application.setCommentsOrNotes(applicationDTO.getCommentsOrNotes());
		application.setStartDate(applicationDTO.getStartDate());
		application.setEndDate(applicationDTO.getEndDate());
		Task task = taskRepository.findOne(taskId);
		application.setTask(task);
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

		// Send mail to Subscriber
		EmailDTO emailDTO = new EmailDTO();
		emailDTO.setMailReciever(userInfo.getEmail());
		emailDTO.setSubject("Task Applied Successfully");
		emailDTO.setMailbody("You have applied for Task :: " + task.getTaskName());
		emailNotofication.sendEmail(emailDTO);

		// Send mail to Poster
		emailDTO = new EmailDTO();
		UserInfo poster = userRepository.findBySoeId(task.getTaskCreatedBy());
		emailDTO.setMailReciever(poster.getEmail());
		emailDTO.setSubject("New Application recieved for Task :" + task.getTaskName());
		emailDTO.setMailbody(userInfo.getName() + " has applied for Task :: " + application.getTask().getTaskName());
		emailNotofication.sendEmail(emailDTO);

		return true;
	}

}
