package com.citi.quest.api.dtos;

import com.citi.quest.api.domain.Notification;
import com.citi.quest.api.domain.UserInfo;

public class NotificationResponseDTO {

	Notification notification;

	UserInfo userInfo;

	ApplicationDTO application;

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
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
