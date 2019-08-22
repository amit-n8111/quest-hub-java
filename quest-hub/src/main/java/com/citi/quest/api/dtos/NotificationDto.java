package com.citi.quest.api.dtos;

import org.springframework.data.mongodb.core.mapping.Document;

public class NotificationDto {

	Long taskId;
	
	String taskName;
	
	String userId;
	
	String userName;
	
	Boolean isViewed = false;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getInterestedUser() {
		return userName;
	}

	public void setInterestedUser(String interestedUser) {
		this.userName = interestedUser;
	}

	public Boolean getIsViewed() {
		return isViewed;
	}

	public void setIsViewed(Boolean isViewed) {
		this.isViewed = isViewed;
	}
	
	
}
