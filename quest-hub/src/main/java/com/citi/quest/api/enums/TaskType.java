package com.citi.quest.api.enums;

public enum TaskType {
	MICRO_TASK("microTask"), MICRO_PROJECT("microProject");
	
	private String typeOfTask;
	
	private TaskType(String type) {
		typeOfTask = type;
	}

	public String getTypeOfTask() {
		return typeOfTask;
	}
}
