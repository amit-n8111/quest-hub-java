package com.citi.quest.api.enums;

public enum TaskStatus {

	OPEN("open"), IN_PROGRESS("in progress"), COMPLETED("completed");
	
	private String taskStatus;
	
	private TaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	public String getStatus() {
		return taskStatus;
	}
}
