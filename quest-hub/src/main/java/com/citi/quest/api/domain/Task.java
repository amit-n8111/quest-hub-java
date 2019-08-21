package com.citi.quest.api.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.citi.quest.api.enums.TaskType;

@Document
public class Task implements Serializable {

	private static final long serialVersionUID = 2997245778895520149L;

	@Id
	Long taskId;

	String taskName;

	Long taskTopicId;

	Integer taskStatusId;

	String taskDescription;

	TaskType taskType;

	Date taskDueDate;

	Date taskCreateDate;

	String taskCreatedBy;

	List<Question> screeningQuestions;

	List<Skill> skills;

	String taskAssignedTo;

	String taskFeedBack;

	Integer manHoursNeeded;

	Integer rewardTypeId;

	Integer rating;

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

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

	public Long getTopicId() {
		return taskTopicId;
	}

	public void setTopicId(Long taskTopicId) {
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

	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
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

	public Long getTaskTopicId() {
		return taskTopicId;
	}

	public void setTaskTopicId(Long taskTopicId) {
		this.taskTopicId = taskTopicId;
	}

	public String getTaskAssignedTo() {
		return taskAssignedTo;
	}

	public void setTaskAssignedTo(String taskAssignedTo) {
		this.taskAssignedTo = taskAssignedTo;
	}

	public String getTaskFeedBack() {
		return taskFeedBack;
	}

	public void setTaskFeedBack(String taskFeedBack) {
		this.taskFeedBack = taskFeedBack;
	}

}
