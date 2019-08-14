package com.citi.quest.api.enums;

public enum TaskType {
	MICRO_TASK("MICRO_TASK"), MICRO_PROJECT("MICRO_TASK");

	private String typeOfTask;

	private TaskType(String type) {
		typeOfTask = type;
	}

	public String getTypeOfTask() {
		return typeOfTask;
	}
}
