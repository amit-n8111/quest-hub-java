package com.citi.quest.api.dtos;

import java.util.List;

import com.citi.quest.api.enums.BusinessUnit;

public class SearchUserResponseDTO {

	String soeId;

	String name;

	String email;

	String teamName;

	String location;

	String managerSoeId;

	BusinessUnit buName;

	Integer totalHoursWorked;

	Integer numberOfTasksCompleted;

	Double rating;

	Integer numberOfReviews;

	List<SkillDetailsDTO> skillDetails;

	List<WorkHistoryAndFeedbackDTO> workHistoryAndFeedbackDTO;

	String userDescription;

	// availability
	// userDescription

	public String getSoeId() {
		return soeId;
	}

	public void setSoeId(String soeId) {
		this.soeId = soeId;
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

	public BusinessUnit getBuName() {
		return buName;
	}

	public void setBuName(BusinessUnit buName) {
		this.buName = buName;
	}

	public Integer getTotalHoursWorked() {
		return totalHoursWorked;
	}

	public void setTotalHoursWorked(Integer totalHoursWorked) {
		this.totalHoursWorked = totalHoursWorked;
	}

	public Integer getNumberOfTasksCompleted() {
		return numberOfTasksCompleted;
	}

	public void setNumberOfTasksCompleted(Integer numberOfTasksCompleted) {
		this.numberOfTasksCompleted = numberOfTasksCompleted;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Integer getNumberOfReviews() {
		return numberOfReviews;
	}

	public void setNumberOfReviews(Integer numberOfReviews) {
		this.numberOfReviews = numberOfReviews;
	}

	public List<SkillDetailsDTO> getSkillDetails() {
		return skillDetails;
	}

	public void setSkillDetails(List<SkillDetailsDTO> skillDetails) {
		this.skillDetails = skillDetails;
	}

	public List<WorkHistoryAndFeedbackDTO> getWorkHistoryAndFeedbackDTO() {
		return workHistoryAndFeedbackDTO;
	}

	public void setWorkHistoryAndFeedbackDTO(List<WorkHistoryAndFeedbackDTO> workHistoryAndFeedbackDTO) {
		this.workHistoryAndFeedbackDTO = workHistoryAndFeedbackDTO;
	}

	public String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getManagerSoeId() {
		return managerSoeId;
	}

	public void setManagerSoeId(String managerSoeId) {
		this.managerSoeId = managerSoeId;
	}

}
