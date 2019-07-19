package com.citi.quest.api.domain;

import java.util.List;

public class Topic extends AbstractDocument{
	
	String topicName;
	
	List<Skills> skills;
	/**
	 * @return the skills
	 */
	public List<Skills> getSkills() {
		return skills;
	}

	/**
	 * @param skills the skills to set
	 */
	public void setSkills(List<Skills> skills) {
		this.skills = skills;
	}

	/**
	 * @return the topicName
	 */
	public String getTopicName() {
		return topicName;
	}

	/**
	 * @param topicName the topicName to set
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	
}
