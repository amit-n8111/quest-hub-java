package com.citi.quest.api.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.citi.quest.api.enums.BusinessUnit;
import com.citi.quest.api.enums.TaskStatus;
import com.citi.quest.api.enums.TaskType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Document
public class Task extends AbstractDocument {

	// String taskId;

	String taskName ;

	String taskDescription ;

	// File attachment;

	TaskType taskType;

	TaskStatus status;

	// String taskPosterName;

	List<UserInfo> interestedSeekers ;

	UserInfo assignedSeeker;

	List<Skills> skillsRequired ;

	Topic topic;

	BusinessUnit buName;

	Integer manHours;

	Date startDate;

	Date endDate;

	List<SoftwareTools> toolsRequired ;

	UserInfo createdBy;
	
	List<Questions> questions;

	@JsonIgnore
	@CreatedDate
	LocalDateTime createdDate ;
	
	@JsonIgnore
	@LastModifiedDate
	private LocalDateTime lastModifiedDate;

	/**
	 * @return the questions
	 */
	public List<Questions> getQuestions() {
		return questions;
	}

	/**
	 * @param questions the questions to set
	 */
	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}

	Boolean isTemplate;
	
	String templateName;
	
	/**
	 * @return the isTemplate
	 */
	public Boolean getIsTemplate() {
		return isTemplate;
	}

	/**
	 * @param isTemplate the isTemplate to set
	 */
	public void setIsTemplate(Boolean isTemplate) {
		this.isTemplate = isTemplate;
	}

	/**
	 * @return the templateName
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * @param templateName the templateName to set
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * @return the lastModifiedDate
	 */
	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * @param lastModifiedDate the lastModifiedDate to set
	 */
	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * @return the taskDescription
	 */
	public String getTaskDescription() {
		return taskDescription;
	}

	/**
	 * @return the createdBy
	 */
	public UserInfo getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(UserInfo createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @param taskDescription the taskDescription to set
	 */
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	/**
	 * @return the taskType
	 */
	public TaskType getTaskType() {
		return taskType;
	}

	/**
	 * @param taskType the taskType to set
	 */
	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	/**
	 * @return the status
	 */
	public TaskStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	/**
	 * @return the interestedSeekers
	 */
	public List<UserInfo> getInterestedSeekers() {
		return interestedSeekers;
	}

	/**
	 * @param interestedSeekers the interestedSeekers to set
	 */
	public void setInterestedSeekers(List<UserInfo> interestedSeekers) {
		this.interestedSeekers = interestedSeekers;
	}

	/**
	 * @return the assignedSeeker
	 */
	public UserInfo getAssignedSeeker() {
		return assignedSeeker;
	}

	/**
	 * @param assignedSeeker the assignedSeeker to set
	 */
	public void setAssignedSeeker(UserInfo assignedSeeker) {
		this.assignedSeeker = assignedSeeker;
	}

	/**
	 * @return the skillsRequired
	 */
	public List<Skills> getSkillsRequired() {
		return skillsRequired;
	}

	/**
	 * @param skillsRequired the skillsRequired to set
	 */
	public void setSkillsRequired(List<Skills> skillsRequired) {
		this.skillsRequired = skillsRequired;
	}

	/**
	 * @return the topic
	 */
	public Topic getTopic() {
		return topic;
	}

	/**
	 * @param topic the topic to set
	 */
	public void setTopic(Topic topic) {
		this.topic = topic;
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
	 * @return the manHours
	 */
	public Integer getManHours() {
		return manHours;
	}

	/**
	 * @param manHours the manHours to set
	 */
	public void setManHours(Integer manHours) {
		this.manHours = manHours;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the toolsRequired
	 */
	public List<SoftwareTools> getToolsRequired() {
		return toolsRequired;
	}

	/**
	 * @param toolsRequired the toolsRequired to set
	 */
	public void setToolsRequired(List<SoftwareTools> toolsRequired) {
		this.toolsRequired = toolsRequired;
	}

}
