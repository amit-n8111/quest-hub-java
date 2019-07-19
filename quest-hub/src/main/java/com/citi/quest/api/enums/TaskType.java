package com.citi.quest.api.enums;

public enum TaskType {
	MICRO_TASK("Micro Task"), MICRO_PROJECT("Micro Project");
	
	private String typeOfTask;
	
	private TaskType(String type) {
		typeOfTask = type;
	}

	public String getTypeOfTask() {
		return typeOfTask;
	}
}
