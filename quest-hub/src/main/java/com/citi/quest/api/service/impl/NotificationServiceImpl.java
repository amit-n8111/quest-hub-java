package com.citi.quest.api.service.impl;

import java.util.ArrayList;
import java.util.List;
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
import com.citi.quest.api.dtos.SkillDetailsDTO;
import com.citi.quest.api.enums.TaskStatus;
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

	@Autowired
	RequestScopeBean requestScopeBean;

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
				notificationResponseList.add(prepareNotificationDTO(notification));
			}
			notificationResponseList.stream().forEach(a-> a.setUserScore(( a.getUserScore()*100 ) / requestScopeBean.getMaxUserScroeForTask()));
		}
		return notificationResponseList;

	}

	private NotificationDTO prepareNotificationDTO(Notification notification) {
		NotificationDTO notificationDTO = new NotificationDTO();
		fillNotificationDTO(notification, notificationDTO);
		Query query = new Query();
		query.addCriteria(Criteria.where("taskStatusId").is(TaskStatus.COMPLETED.getId()));
		query.addCriteria(Criteria.where("taskAssignedTo").is(notification.getUserSoeId()));
		List<Task> tasksDoneByUser = mongoOperations.find(query, Task.class);
		System.out.println("tasksDoneByUser " + tasksDoneByUser.size());
		UserInfo notificationUser = userRepository.findBySoeId(notification.getUserSoeId());
		Integer totalHoursWorked = calculateTotalWorkHoursOfUser(tasksDoneByUser);
		Float profileMatchScore = generateUserScroeForTask(tasksDoneByUser, notification, notificationUser);
		notificationDTO.setTotalHoursWorked(totalHoursWorked);
		notificationDTO.setNumberOfTasksCompleted(tasksDoneByUser.size());
		notificationDTO.setUserScore(profileMatchScore);
		return notificationDTO;
	}

	private Float generateUserScroeForTask(List<Task> tasksDoneByUser, Notification notification,
			UserInfo notificationUser) {

		Task currTask = taskRepository.findByTaskId(notification.getTaskId());
		List<String> taskSkills = currTask.getSkills().stream().map(a -> a.getName()).collect(Collectors.toList());
		List<SkillDetailsDTO> matchingSkillsdto = notificationUser.getSkillDetails().stream()
				.filter(s -> taskSkills.contains(s.getSkill().getName())).collect(Collectors.toList());

		float skillMatchScore = 0f, avgTaskRatingScore = 1f;
		if (taskSkills.size() != 0) {
			skillMatchScore = matchingSkillsdto.size() / taskSkills.size();
		}

		float releventExperienceScore = matchingSkillsdto.stream().map(skill -> {
			Integer level = skill.getLevel() == null ? null : skill.getLevel().getId();
			Integer exp = skill.getYearsOfExperience();
			if (level != null && exp != null)
				return skill.getLevel().getId() * skill.getYearsOfExperience();
			else if (level != null)
				return skill.getLevel().getId();
			else if (exp != null)
				return skill.getYearsOfExperience();
			else {
				return 1;
			}
		}).reduce(0, Integer::sum) / matchingSkillsdto.size();
		if (tasksDoneByUser.size() != 0) {
			avgTaskRatingScore = (float) tasksDoneByUser.stream().map(task -> task.getRating()).reduce(0, Integer::sum)
					/ (float) tasksDoneByUser.size();
		}
		System.out.printf("skillMatchScore %s \n avgTaskRatingScore %s \n", skillMatchScore, avgTaskRatingScore);
		float currUserScore = (skillMatchScore * avgTaskRatingScore * releventExperienceScore);
		if (currUserScore > requestScopeBean.getMaxUserScroeForTask())
			requestScopeBean.setMaxUserScroeForTask(currUserScore);
		return currUserScore;
	}

	private Integer calculateTotalWorkHoursOfUser(List<Task> tasks) {
		int totalHoursWorked = 0;
		for (Task task : tasks) {
			totalHoursWorked += task.getManHoursNeeded();
		}
		System.out.printf("task size= %s , totalHoursWorked= %s \n", tasks.size(), totalHoursWorked);
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
