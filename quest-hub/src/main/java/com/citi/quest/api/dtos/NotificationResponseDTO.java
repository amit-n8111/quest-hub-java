package com.citi.quest.api.dtos;

import java.util.List;

import com.citi.quest.api.domain.UserInfo;

public class NotificationResponseDTO {

	NotificationDTO notification;

	UserInfo userInfo;

	ApplicationDTO application;

	List<OtherUserInfoDTO> otherUserInfo;

	public List<OtherUserInfoDTO> getOtherUserInfo() {
		return otherUserInfo;
	}

	public void setOtherUserInfo(List<OtherUserInfoDTO> otherUserInfo) {
		this.otherUserInfo = otherUserInfo;
	}

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