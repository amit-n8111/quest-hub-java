package com.citi.quest.api.dtos;

import java.util.Date;

public class NotificationDTO {
	
	Long notId;

	Long taskId;

	String taskName;

	String userId;

	String userName;

	Boolean isViewed = false;

	Date notificationTime;

	Integer numberOfTasksCompleted;

	Integer totalHoursWorked;

	Double rating;

	Integer numberOfReviews;

	public Integer getNumberOfTasksCompleted() {
		return numberOfTasksCompleted;
	}

	public void setNumberOfTasksCompleted(Integer numberOfTasksCompleted) {
		this.numberOfTasksCompleted = numberOfTasksCompleted;
	}

	public Integer getTotalHoursWorked() {
		return totalHoursWorked;
	}

	public void setTotalHoursWorked(Integer totalHoursWorked) {
		this.totalHoursWorked = totalHoursWorked;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Integer getNumberOfReviews() {
		return numberOfReviews;
	}

	public void setNumberOfReviews(Integer numberOfReviews) {
		this.numberOfReviews = numberOfReviews;
	}

	public Date getNotificationTime() {
		return notificationTime;
	}

	public void setNotificationTime(Date notificationTime) {
		this.notificationTime = notificationTime;
	}

	public Long getNotId() {
		return notId;
	}

	public void setNotId(Long notId) {
		this.notId = notId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Boolean getIsViewed() {
		return isViewed;
	}

	public void setIsViewed(Boolean isViewed) {
		this.isViewed = isViewed;
	}

}
