package com.citi.quest.api.enums;

public enum TaskStatus {

	DRAFT(1, "Draft"), PUBLISHED(2, "Published"), ASSIGNED(3,"Assigned"),  IN_PROGRESS(4, "In Progress"), COMPLETED(5, "Completed");
	
	private Integer id;
	private String taskStatus;
	
	private TaskStatus(Integer id, String taskStatus) {
		this.id = id;
		this.taskStatus = taskStatus;
	}
	
	public String getStatus() {
		return taskStatus;
	}

	public Integer getId() {
		return id;
	}
	
}
