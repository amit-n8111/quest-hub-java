package com.citi.quest.api.enums;

public enum ExpertiseLevel {

	BEGINNER("beginner", 1), Intermediate("intermediate", 2), EXPERT("expert", 3);

	private int id;
	private String level;

	private ExpertiseLevel(String level, int id) {
		this.id = id;
		this.level = level;
	}

	public String getLevel() {
		return level;
	}

	public int getId() {
		return id;
	}

}
