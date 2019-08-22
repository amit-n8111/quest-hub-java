package com.citi.quest.api.dtos;

import java.util.Date;
import java.util.List;

import com.citi.quest.api.domain.Question;
import com.citi.quest.api.domain.Skill;
import com.citi.quest.api.domain.UserInfo;

public class TaskResponseDTO {
	
	private boolean isMarkedAsFavorite;
	private boolean isAlreadyApplied;
	private int totalTasks;
	private Long taskId;
	private String taskName;
	private String taskTopicName;
	private Long taskTopicId;
	private String taskStatusName;
	private Integer taskStatusId;
	private String taskDescription;
	private String taskTypeName;
	private String taskType;
	private Date taskDueDate;
	private Date taskCreatedDate;
	private int numberOfApplicationRecieved;
	private Integer manHoursNeeded;
	private String rewardType;
	private Integer rewardTypeId;
	private List<Skill> taskSkills;
	private List<Question> screeningQuestions;
	private UserInfo taskCreatedBy;
	
	public boolean isMarkedAsFavorite() {
		return isMarkedAsFavorite;
	}
	public void setMarkedAsFavorite(boolean isMarkedAsFavorite) {
		this.isMarkedAsFavorite = isMarkedAsFavorite;
	}
	public boolean isAlreadyApplied() {
		return isAlreadyApplied;
	}
	public void setAlreadyApplied(boolean isAlreadyApplied) {
		this.isAlreadyApplied = isAlreadyApplied;
	}
	public int getTotalTasks() {
		return totalTasks;
	}
	public void setTotalTasks(int totalTasks) {
		this.totalTasks = totalTasks;
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
	public String getTaskTopicName() {
		return taskTopicName;
	}
	public void setTaskTopicName(String taskTopicName) {
		this.taskTopicName = taskTopicName;
	}
	public Long getTaskTopicId() {
		return taskTopicId;
	}
	public void setTaskTopicId(Long taskTopicId) {
		this.taskTopicId = taskTopicId;
	}
	public String getTaskStatusName() {
		return taskStatusName;
	}
	public void setTaskStatusName(String taskStatusName) {
		this.taskStatusName = taskStatusName;
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
	public String getTaskTypeName() {
		return taskTypeName;
	}
	public void setTaskTypeName(String taskTypeName) {
		this.taskTypeName = taskTypeName;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public Date getTaskDueDate() {
		return taskDueDate;
	}
	public void setTaskDueDate(Date taskDueDate) {
		this.taskDueDate = taskDueDate;
	}
	public Date getTaskCreatedDate() {
		return taskCreatedDate;
	}
	public void setTaskCreatedDate(Date taskCreatedDate) {
		this.taskCreatedDate = taskCreatedDate;
	}
	public int getNumberOfApplicationRecieved() {
		return numberOfApplicationRecieved;
	}
	public void setNumberOfApplicationRecieved(int numberOfApplicationRecieved) {
		this.numberOfApplicationRecieved = numberOfApplicationRecieved;
	}
	public Integer getManHoursNeeded() {
		return manHoursNeeded;
	}
	public void setManHoursNeeded(Integer manHoursNeeded) {
		this.manHoursNeeded = manHoursNeeded;
	}
	public String getRewardType() {
		return rewardType;
	}
	public void setRewardType(String rewardType) {
		this.rewardType = rewardType;
	}
	public Integer getRewardTypeId() {
		return rewardTypeId;
	}
	public void setRewardTypeId(Integer rewardTypeId) {
		this.rewardTypeId = rewardTypeId;
	}
	public List<Skill> getTaskSkills() {
		return taskSkills;
	}
	public void setTaskSkills(List<Skill> taskSkills) {
		this.taskSkills = taskSkills;
	}
	public List<Question> getScreeningQuestions() {
		return screeningQuestions;
	}
	public void setScreeningQuestions(List<Question> screeningQuestions) {
		this.screeningQuestions = screeningQuestions;
	}
	public UserInfo getTaskCreatedBy() {
		return taskCreatedBy;
	}
	public void setTaskCreatedBy(UserInfo taskCreatedBy) {
		this.taskCreatedBy = taskCreatedBy;
	}
	

}
