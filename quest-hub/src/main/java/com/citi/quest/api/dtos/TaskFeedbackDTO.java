package com.citi.quest.api.dtos;

public class TaskFeedbackDTO {

	Long taskId;

	Integer rating;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

}
