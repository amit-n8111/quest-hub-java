package com.citi.quest.api.service;

import java.util.List;

import com.citi.quest.api.dtos.ActiveNotification;
import com.citi.quest.api.dtos.NotificationResponseDTO;

public interface NotificationService {

	ActiveNotification getAllActiveNotifications(String user);

	List<NotificationResponseDTO> getAllNotifications(String User);
}
