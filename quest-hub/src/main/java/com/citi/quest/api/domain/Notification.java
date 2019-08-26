package com.citi.quest.api.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document
public class Notification {

	@Id
	Long id;

	@JsonProperty
	Long notificationId;

	Long taskId;

	String taskName;

	String taskOwner;

	String userSoeId;

	String userName;

	Long aplicationId;

	Boolean isViewed = false;
	
	Date notificationTime;

	public Date getNotificationTime() {
		return notificationTime;
	}

	public void setNotificationTime(Date notificationTime) {
		this.notificationTime = notificationTime;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNotificationId() {
		return id;
	}

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

	public String getTaskOwner() {
		return taskOwner;
	}

	public void setTaskOwner(String taskOwner) {
		this.taskOwner = taskOwner;
	}

	public String getUserSoeId() {
		return userSoeId;
	}

	public void setUserSoeId(String userSoeId) {
		this.userSoeId = userSoeId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getIsViewed() {
		return isViewed;
	}

	public void setIsViewed(Boolean isViewed) {
		this.isViewed = isViewed;
	}

	public Long getAplicationId() {
		return aplicationId;
	}

	public void setAplicationId(Long aplicationId) {
		this.aplicationId = aplicationId;
	}

}
