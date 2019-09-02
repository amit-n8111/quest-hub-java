package com.citi.quest.api.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

import com.citi.quest.api.dtos.SkillDetailsDTO;
import com.citi.quest.api.enums.BusinessUnit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document
public class UserInfo {

	@Id
	String soeId;

	@JsonProperty("userSoeId")
	String userSoeId;

	String name;

	String email;

	String location;

	String teamName;

	BusinessUnit buName;

	List<SkillDetailsDTO> skillDetails;

	List<Topic> topicsSubscribed;

	String managerSoeId;

	Double rating;

	String userDescription;
	
	@JsonIgnore
	@TextScore
	Float score;

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	public String getUserSoeId() {
		return soeId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<SkillDetailsDTO> getSkillDetails() {
		return skillDetails;
	}

	public void setSkillDetails(List<SkillDetailsDTO> skillDetails) {
		this.skillDetails = skillDetails;
	}

	public String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

}
