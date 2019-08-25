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
import com.citi.quest.api.domain.UserInfo;
import com.citi.quest.api.dtos.ActiveNotification;
import com.citi.quest.api.dtos.ApplicationDTO;
import com.citi.quest.api.dtos.NotificationResponseDTO;
import com.citi.quest.api.repositories.ApplicationRepository;
import com.citi.quest.api.repositories.NotificationRepository;
import com.citi.quest.api.repositories.UserInfoRepository;
import com.citi.quest.api.service.NotificationService;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

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

		Criteria criteria = new Criteria();
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
	public List<NotificationResponseDTO> getAllNotifications(String user) {
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

}
