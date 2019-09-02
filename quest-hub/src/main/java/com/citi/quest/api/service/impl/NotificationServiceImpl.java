package com.citi.quest.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citi.quest.api.domain.Notification;
import com.citi.quest.api.domain.Task;
import com.citi.quest.api.domain.UserInfo;
import com.citi.quest.api.dtos.ActiveNotification;
import com.citi.quest.api.dtos.ApplicationDTO;
import com.citi.quest.api.dtos.NotificationDTO;
import com.citi.quest.api.dtos.NotificationDetailsDTO;
import com.citi.quest.api.dtos.NotificationResponseDTO;
import com.citi.quest.api.repositories.ApplicationRepository;
import com.citi.quest.api.repositories.NotificationRepository;
import com.citi.quest.api.repositories.TaskRepository;
import com.citi.quest.api.repositories.UserInfoRepository;
import com.citi.quest.api.service.NotificationService;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	UserInfoRepository userRepository;

	@Autowired
	ApplicationRepository applicationRepository;

	@Autowired
	MongoOperations mongoOperations;

	@Autowired
	NotificationRepository notificationRepository;

	@Override
	public ActiveNotification getAllActiveNotifications(String user) {
		ActiveNotification actNotifications = new ActiveNotification();

		Query query = new Query();

		if (StringUtils.isNotBlank(user)) {
			query.addCriteria(Criteria.where("taskOwner").is(user));
		}

		query.addCriteria(Criteria.where("isViewed").is(false));

		List<Notification> notifications = mongoOperations.find(query, Notification.class);

		actNotifications.setCount(notifications.size());
		actNotifications.setNotifications(notifications);
		return actNotifications;
	}

	@Override
	public List<NotificationDTO> getAllNotifications(String notificationOwner) {

		List<NotificationDTO> notificationResponseList = new ArrayList<NotificationDTO>();

		Query query = new Query();
		if (StringUtils.isNotBlank(notificationOwner)) {
			query.addCriteria(Criteria.where("taskOwner").is(notificationOwner));
		}

		List<Notification> notifications = mongoOperations.find(query, Notification.class);

		if (null != notifications) {
			for (Notification notification : notifications) {

				NotificationDTO notificationDTO = new NotificationDTO();
				fillNotificationDTO(notification, notificationDTO);
				List<Task> tasksDoneByUser = taskRepository.findByTaskAssignedToAndTaskStatusId(notification.getUserSoeId(), 5);
				UserInfo notificationUser = userRepository.findBySoeId(notification.getUserSoeId());
				Integer totalHoursWorked = calculateTotalWorkHoursOfUser(tasksDoneByUser);
				Float profileMatchScore = generateUserProfileScroeForTask(tasksDoneByUser,notification,notificationUser);
				notificationDTO.setTotalHoursWorked(totalHoursWorked);
				notificationDTO.setNumberOfTasksCompleted(tasksDoneByUser.size());
				notificationDTO.setUserScore(profileMatchScore);
				notificationResponseList.add(notificationDTO);
			}
		}

		return notificationResponseList;

	}

	private Float generateUserProfileScroeForTask(List<Task> tasksDoneByUser, Notification notification, UserInfo notificationUser) {
		
		Task currTask = taskRepository.findByTaskId(notification.getTaskId());
		List<String> taskSkills = currTask.getSkills().stream().map(a->a.getName()).collect(Collectors.toList());
		List<String> userSkills = notificationUser.getSkillDetails().stream().map(a->a.getSkill().getName()).collect(Collectors.toList());
		userSkills.retainAll(taskSkills);
		float skillMatchScore = 0f ,avgTaskRatingScore = 0f;
		if(taskSkills.size() != 0){
			skillMatchScore = (float)userSkills.size()/(float)taskSkills.size();
		}
		if(tasksDoneByUser.size() != 0) {
			avgTaskRatingScore = (float)tasksDoneByUser.stream().map(task-> task.getScore()).reduce(Float::sum).get()/(float)tasksDoneByUser.size();
		}
		
		return (skillMatchScore)*(avgTaskRatingScore);
	}

	private Integer calculateTotalWorkHoursOfUser(List<Task> tasks) {
		Integer totalHoursWorked=0;		
		for (Task task : tasks) {
			totalHoursWorked += task.getManHoursNeeded();
		}
		return totalHoursWorked;
	}

	@Override
	public NotificationDetailsDTO getNotificationDetail(String user, Long notificationId) {

		Query query = new Query();
		if (StringUtils.isNotBlank(user)) {
			query.addCriteria(Criteria.where("taskOwner").is(user));
		}
		if (notificationId != null && notificationId > 0) {
			query.addCriteria(Criteria.where("notificationId").is(notificationId));
		}

		Notification notification = mongoOperations.findOne(query, Notification.class);
		UserInfo userInfo = userRepository.findBySoeId(notification.getUserSoeId());
		ApplicationDTO application = applicationRepository.findById(notification.getAplicationId());

		NotificationDTO notificationDTO = new NotificationDTO();
		fillNotificationDTO(notification, notificationDTO);
		List<Task> tasks = taskRepository.findByTaskAssignedToAndTaskStatusId(user, 5);
		Integer totalHoursWorked = 0;
		for (Task task : tasks) {
			totalHoursWorked += task.getManHoursNeeded();
		}
		notificationDTO.setTotalHoursWorked(totalHoursWorked);
		notificationDTO.setNumberOfTasksCompleted(tasks.size());

		NotificationDetailsDTO notificationResponse = new NotificationDetailsDTO();
		notificationResponse.setNotification(notificationDTO);
		notificationResponse.setUserInfo(userInfo);
		notificationResponse.setApplication(application);

		return notificationResponse;

	}

	@Override
	public List<NotificationResponseDTO> getAllNotifications1(String user) {
		List<NotificationResponseDTO> notificationResponseList = new ArrayList<NotificationResponseDTO>();

		Criteria criteria = new Criteria();
		Query query = new Query();

		if (StringUtils.isNotBlank(user)) {
			query.addCriteria(Criteria.where("taskOwner").is(user));
		}

		List<Notification> notifications = mongoOperations.find(query, Notification.class);

		if (null != notifications) {
			for (Notification notification : notifications) {
				NotificationResponseDTO notificationResponse = new NotificationResponseDTO();

				UserInfo userInfo = userRepository.findBySoeId(notification.getUserSoeId());

				ApplicationDTO application = applicationRepository.findById(notification.getAplicationId());

				notificationResponse.setNotification(notification);
				notificationResponse.setApplication(application);
				notificationResponse.setUserInfo(userInfo);

				notificationResponseList.add(notificationResponse);
			}

		}

		return notificationResponseList;
	}

	private void fillNotificationDTO(Notification notification, NotificationDTO notificationDTO) {

		notificationDTO.setNotId(notification.getNotificationId());
		notificationDTO.setTaskId(notification.getTaskId());
		notificationDTO.setTaskName(notification.getTaskName());
		notificationDTO.setUserId(notification.getUserSoeId());
		notificationDTO.setUserName(notification.getUserName());
		notificationDTO.setIsViewed(notification.getIsViewed());
		notificationDTO.setNotificationTime(notification.getNotificationTime());
	}

}
