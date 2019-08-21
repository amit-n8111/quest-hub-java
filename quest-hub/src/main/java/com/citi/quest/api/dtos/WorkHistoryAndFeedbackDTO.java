package com.citi.quest.api.dtos;

import java.util.Date;

public class WorkHistoryAndFeedbackDTO {
	
	String taskName;
	
	String taskCreateBy;
	
	Date taskCreateDate;
	
	String TaskFeedBack;
	
	Integer rating;

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskCreateBy() {
		return taskCreateBy;
	}

	public void setTaskCreateBy(String taskCreateBy) {
		this.taskCreateBy = taskCreateBy;
	}

	public Date getTaskCreateDate() {
		return taskCreateDate;
	}

	public void setTaskCreateDate(Date taskCreateDate) {
		this.taskCreateDate = taskCreateDate;
	}

	public String getTaskFeedBack() {
		return TaskFeedBack;
	}

	public void setTaskFeedBack(String taskFeedBack) {
		TaskFeedBack = taskFeedBack;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	
	
	

}
