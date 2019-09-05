package com.citi.quest.api.dtos;

import com.citi.quest.api.domain.Skill;
import com.citi.quest.api.enums.ExpertiseLevel;

public class SkillDetailsDTO {

	Skill skill;

	ExpertiseLevel level;

	Integer yearsOfExperience;

	String lastWorked;

	/*Integer numOfTaskCompleted;

	public Integer getNumOfTaskCompleted() {
		return numOfTaskCompleted;
	}

	public void setNumOfTaskCompleted(Integer numOfTaskCompleted) {
		this.numOfTaskCompleted = numOfTaskCompleted;
	}*/

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	/**
	 * @return the level
	 */
	public ExpertiseLevel getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
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
	 * @param yearsOfExperience
	 *            the yearsOfExperience to set
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
	 * @param lastWorked
	 *            the lastWorked to set
	 */
	public void setLastWorked(String lastWorked) {
		this.lastWorked = lastWorked;
	}

}
