package com.citi.quest.api.dtos;

import java.util.Date;

public class NotificationDTO {

	Long id;

	Long taskId;

	String taskName;

	String userSoeId;

	String userName;

	Long aplicationId;

	Boolean isViewed = false;

	Date notificationTime;

	Integer numberOfTasksCompleted;

	Integer totalHoursWorked;

	Double rating;

	Integer numberOfReviews;

	Float userScore;

	public Float getUserScore() {
		return userScore;
	}

	public void setUserScore(Float userScore) {
		this.userScore = userScore;
	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNotificationId() {
		return id;
	}

	public String getUserSoeId() {
		return userSoeId;
	}

	public void setUserSoeId(String userSoeId) {
		this.userSoeId = userSoeId;
	}

	public Long getAplicationId() {
		return aplicationId;
	}

	public void setAplicationId(Long aplicationId) {
		this.aplicationId = aplicationId;
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
