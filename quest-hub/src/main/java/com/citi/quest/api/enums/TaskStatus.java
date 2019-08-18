package com.citi.quest.api.enums;

public enum TaskStatus {

	DRAFT("draft",1), PUBLISHED("published",2), ASSIGNED("assigned",3),  IN_PROGRESS("in_progress",4), COMPLETED("completed",5);
	
	private int id;
	private String taskStatus;
	
	private TaskStatus(String taskStatus,int id) {
		this.id = id;
		this.taskStatus = taskStatus;
	}
	
	public String getStatus() {
		return taskStatus;
	}

	public int getId() {
		return id;
	}
	
}
