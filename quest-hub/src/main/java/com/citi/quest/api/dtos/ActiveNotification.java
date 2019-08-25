package com.citi.quest.api.dtos;

import java.util.List;

import com.citi.quest.api.domain.Notification;

public class ActiveNotification {

	int count;

	List<Notification> notifications;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

}
