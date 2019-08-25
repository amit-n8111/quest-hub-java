package com.citi.quest.api.dtos;

import java.util.Date;
import java.util.List;

import com.citi.quest.api.domain.Question;
import com.citi.quest.api.domain.Skill;

public class TaskDTO {

	Long taskId;

	String taskName;

	Long taskTopicId;

	Integer taskStatusId;

	String taskDescription;

	String taskType;
	
	Integer taskTypeId;

	Date taskDueDate;

	Date taskCreateDate;

	String taskCreatedBy;

	List<Question> screeningQuestions;

	List<Skill> skills;
	
	Integer manHoursNeeded;

	Integer rewardTypeId;
	
	

	public Integer getManHoursNeeded() {
		return manHoursNeeded;
	}

	public void setManHoursNeeded(Integer manHoursNeeded) {
		this.manHoursNeeded = manHoursNeeded;
	}

	public Integer getRewardTypeId() {
		return rewardTypeId;
	}

	public void setRewardTypeId(Integer rewardTypeId) {
		this.rewardTypeId = rewardTypeId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Long getTaskTopicId() {
		return taskTopicId;
	}

	public void setTaskTopicId(Long taskTopicId) {
		this.taskTopicId = taskTopicId;
	}

	public Integer getTaskStatusId() {
		return taskStatusId;
	}

	public void setTaskStatusId(Integer taskStatusId) {
		this.taskStatusId = taskStatusId;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	
	public Integer getTaskTypeId() {
		return taskTypeId;
	}

	public void setTaskTypeId(Integer taskTypeId) {
		this.taskTypeId = taskTypeId;
	}

	public Date getTaskDueDate() {
		return taskDueDate;
	}

	public void setTaskDueDate(Date taskDueDate) {
		this.taskDueDate = taskDueDate;
	}

	public Date getTaskCreateDate() {
		return taskCreateDate;
	}

	public void setTaskCreateDate(Date taskCreateDate) {
		this.taskCreateDate = taskCreateDate;
	}

	public String getTaskCreatedBy() {
		return taskCreatedBy;
	}

	public void setTaskCreatedBy(String taskCreatedBy) {
		this.taskCreatedBy = taskCreatedBy;
	}

	public List<Question> getScreeningQuestions() {
		return screeningQuestions;
	}

	public void setScreeningQuestions(List<Question> screeningQuestions) {
		this.screeningQuestions = screeningQuestions;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	

}
