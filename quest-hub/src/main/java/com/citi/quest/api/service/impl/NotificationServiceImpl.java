package com.citi.quest.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citi.quest.api.domain.Notification;
import com.citi.quest.api.dtos.ActiveNotification;
import com.citi.quest.api.dtos.NotificationDto;
import com.citi.quest.api.repositories.NotificationRepository;
import com.citi.quest.api.service.NotificationService;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	NotificationRepository notificationRepository;
	
	@Override
	public ActiveNotification getAllActiveNotifications(String user) {
		ActiveNotification actNotifications = new ActiveNotification();
		List<NotificationDto> activeNotList = new ArrayList<NotificationDto>();
		Notification notification = notificationRepository.findBySoeId(user);
		if(notification!=null) {
			List<NotificationDto> totalNotifications = notification.getNotifications();
			if(null != totalNotifications && !totalNotifications.isEmpty()) {
				for(NotificationDto entry : totalNotifications) {
					if(!entry.getIsViewed()) {
						activeNotList.add(entry);
					}
				}
			}
		}
		actNotifications.setCount(activeNotList.size());
		actNotifications.setNotifications(activeNotList);
		return actNotifications;
	}

}
