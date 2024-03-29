package com.citi.quest.api.service;

import java.util.List;

import com.citi.quest.api.dtos.ActiveNotification;
import com.citi.quest.api.dtos.NotificationDTO;
import com.citi.quest.api.dtos.NotificationDetailsDTO;
import com.citi.quest.api.dtos.NotificationResponseDTO;

public interface NotificationService {

	ActiveNotification getAllActiveNotifications(String user);

	List<NotificationDTO> getAllNotifications(String User);

	List<NotificationResponseDTO> getAllNotifications1(String User);

	NotificationDetailsDTO getNotificationDetail(String user, Long notificationId);
}
