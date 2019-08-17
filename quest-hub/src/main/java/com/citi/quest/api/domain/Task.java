package com.citi.quest.api.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.citi.quest.api.enums.TaskType;

@Document
public class Task  {
	
	Long taskId;

	String taskName;

	Long taskTopicId;

	Long taskStatusId;

	String taskDescription;

	TaskType taskType;

	Date taskDueDate;

	Date taskCreateDate;

	String taskCreatedBy;

	List<Question> screeningQuestions;

	List<Skill> skills;

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

	public Long getTaskStatusId() {
		return taskStatusId;
	}

	public void setTaskStatusId(Long taskStatusId) {
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

}
