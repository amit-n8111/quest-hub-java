package com.citi.quest.api.service.impl;

import java.util.ArrayList;
import java.util.List;

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
	public List<NotificationDTO> getAllNotifications(String user) {

		List<NotificationDTO> notificationResponseList = new ArrayList<NotificationDTO>();

		Query query = new Query();
		if (StringUtils.isNotBlank(user)) {
			query.addCriteria(Criteria.where("taskOwner").is(user));
		}

		List<Notification> notifications = mongoOperations.find(query, Notification.class);

		if (null != notifications) {
			for (Notification notification : notifications) {

				NotificationDTO notificationDTO = new NotificationDTO();
				fillNotificationDTO(notification, notificationDTO);
				List<Task> tasks = taskRepository.findByTaskAssignedToAndTaskStatusId(user, 5);
				Integer totalHoursWorked = 0;
				for (Task task : tasks) {
					totalHoursWorked += task.getManHoursNeeded();
				}
				notificationDTO.setTotalHoursWorked(totalHoursWorked);
				notificationDTO.setNumberOfTasksCompleted(tasks.size());
				notificationResponseList.add(notificationDTO);
			}
		}

		return notificationResponseList;

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
