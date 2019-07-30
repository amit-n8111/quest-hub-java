package com.citi.quest.api.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import com.citi.quest.api.dtos.SkillDetailsDTO;
import com.citi.quest.api.enums.BusinessUnit;

@Document
public class UserInfo extends AbstractDocument {

	String soeId;

	String firstName;

	String lastName;

	String teamName;

	BusinessUnit buName;

	Map<Skill, SkillDetailsDTO> skills;

	List<Topic> topicsSubscribed;

	String managerSoeId;

	Double rating;

	Map<Date, Integer> availability = null;


	/**
	 * @return the soeId
	 */
	public String getSoeId() {
		return soeId;
	}

	/**
	 * @param soeId the soeId to set
	 */
	public void setSoeId(String soeId) {
		this.soeId = soeId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * @param teamName the teamName to set
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	/**
	 * @return the buName
	 */
	public BusinessUnit getBuName() {
		return buName;
	}

	/**
	 * @param buName the buName to set
	 */
	public void setBuName(BusinessUnit buName) {
		this.buName = buName;
	}


	/**
	 * @return the skills
	 */
	public Map<Skill, SkillDetailsDTO> getSkills() {
		return skills;
	}

	/**
	 * @param skills the skills to set
	 */
	public void setSkills(Map<Skill, SkillDetailsDTO> skills) {
		this.skills = skills;
	}

	/**
	 * @return the topicsSubscribed
	 */
	public List<Topic> getTopicsSubscribed() {
		return topicsSubscribed;
	}

	/**
	 * @param topicsSubscribed the topicsSubscribed to set
	 */
	public void setTopicsSubscribed(List<Topic> topicsSubscribed) {
		this.topicsSubscribed = topicsSubscribed;
	}

	/**
	 * @return the managerSoeId
	 */
	public String getManagerSoeId() {
		return managerSoeId;
	}

	/**
	 * @param managerSoeId the managerSoeId to set
	 */
	public void setManagerSoeId(String managerSoeId) {
		this.managerSoeId = managerSoeId;
	}

	/**
	 * @return the rating
	 */
	public Double getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(Double rating) {
		this.rating = rating;
	}

	/**
	 * @return the availability
	 */
	public Map<Date, Integer> getAvailability() {
		return availability;
	}

	/**
	 * @param availability the availability to set
	 */
	public void setAvailability(Map<Date, Integer> availability) {
		this.availability = availability;
	}

}
