package com.citi.quest.api.dtos;

import com.citi.quest.api.domain.Notification;
import com.citi.quest.api.domain.UserInfo;

public class NotificationDetailsDTO {

	NotificationDTO notification;

	UserInfo userInfo;

	ApplicationDTO application;

	public NotificationDTO getNotification() {
		return notification;
	}

	public void setNotification(NotificationDTO notification) {
		this.notification = notification;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public ApplicationDTO getApplication() {
		return application;
	}

	public void setApplication(ApplicationDTO application) {
		this.application = application;
	}

}
