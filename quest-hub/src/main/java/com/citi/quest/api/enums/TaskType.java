package com.citi.quest.api.enums;

public enum TaskType {
	MICRO_TASK("MICRO_TASK", 1), MICRO_PROJECT("MICRO_TASK", 2);

	private int id;
	private String typeOfTask;

	private TaskType(String type, int id) {
		this.id = id;
		this.typeOfTask = type;
	}

	public String getTypeOfTask() {
		return typeOfTask;
	}

	public int getId() {
		return id;
	}
}
