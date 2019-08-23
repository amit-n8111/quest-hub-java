package com.citi.quest.api.service;

import com.citi.quest.api.dtos.ActiveNotification;

public interface NotificationService {

	ActiveNotification getAllActiveNotifications(String user);
}
