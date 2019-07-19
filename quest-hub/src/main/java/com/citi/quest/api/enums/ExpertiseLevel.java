package com.citi.quest.api.enums;

public enum ExpertiseLevel {
	
	BEGINNER("beginner"), Intermediate("intermediate"), EXPERT("expert");
	
	private String level;
	
	private ExpertiseLevel(String level) {
		this.level = level;
	}
	
	public String getLevel() {
		return level;
	}
}
