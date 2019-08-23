package com.citi.quest.api.enums;

public enum TaskType {
	MICRO_TASK(1, "Micro Task"), MICRO_PROJECT(2, "Micro Project");

	private Integer id;
	private String typeOfTask;

	private TaskType(Integer id, String type) {
		this.id = id;
		this.typeOfTask = type;
	}

	public String getTypeOfTask() {
		return typeOfTask;
	}

	public Integer getId() {
		return id;
	}
}
