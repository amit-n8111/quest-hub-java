package com.citi.quest.api.domain;

import com.citi.quest.api.enums.ExpertiseLevel;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Skills extends AbstractDocument{
	
	String name;
	
	ExpertiseLevel level;
	
	Integer yearsOfExperience;
	
	String lastWorked;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the level
	 */
	public ExpertiseLevel getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(ExpertiseLevel level) {
		this.level = level;
	}

	/**
	 * @return the yearsOfExperience
	 */
	public Integer getYearsOfExperience() {
		return yearsOfExperience;
	}

	/**
	 * @param yearsOfExperience the yearsOfExperience to set
	 */
	public void setYearsOfExperience(Integer yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	/**
	 * @return the lastWorked
	 */
	public String getLastWorked() {
		return lastWorked;
	}

	/**
	 * @param lastWorked the lastWorked to set
	 */
	public void setLastWorked(String lastWorked) {
		this.lastWorked = lastWorked;
	}
}
