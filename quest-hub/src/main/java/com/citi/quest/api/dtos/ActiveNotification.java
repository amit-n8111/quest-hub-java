package com.citi.quest.api.dtos;

import java.util.List;

public class ActiveNotification {

	int count;
	
	List<NotificationDto> notifications;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<NotificationDto> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<NotificationDto> notifications) {
		this.notifications = notifications;
	}
	
	
}
