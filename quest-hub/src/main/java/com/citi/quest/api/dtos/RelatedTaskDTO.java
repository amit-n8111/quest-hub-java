package com.citi.quest.api.dtos;

public class RelatedTaskDTO {

	Long Id;

	String taskName;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

}
