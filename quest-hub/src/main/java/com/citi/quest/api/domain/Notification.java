package com.citi.quest.api.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.citi.quest.api.dtos.NotificationDto;
@Document
public class Notification{

	@Id
	String soeId;
	
	List<NotificationDto> notifications;

	public String getSoeId() {
		return soeId;
	}

	public void setSoeId(String soeId) {
		this.soeId = soeId;
	}

	public List<NotificationDto> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<NotificationDto> notifications) {
		this.notifications = notifications;
	}
	
	
}
