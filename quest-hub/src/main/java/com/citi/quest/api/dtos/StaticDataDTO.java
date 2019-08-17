package com.citi.quest.api.dtos;

import com.citi.quest.api.domain.Topic;
import com.citi.quest.api.enums.RewardType;
import com.citi.quest.api.enums.TaskStatus;
import com.citi.quest.api.enums.TaskType;

public class StaticDataDTO {

	TaskStatus[] taskStatus = TaskStatus.values();

	TaskType[] taskType = TaskType.values();

	RewardType[] rewardType = RewardType.values();

	Topic[] topic;

	public TaskStatus[] getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus[] taskStatus) {
		this.taskStatus = taskStatus;
	}

	public TaskType[] getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType[] taskType) {
		this.taskType = taskType;
	}

	public RewardType[] getRewardType() {
		return rewardType;
	}

	public void setRewardType(RewardType[] rewardType) {
		this.rewardType = rewardType;
	}

	public Topic[] getTopic() {
		return topic;
	}

	public void setTopic(Topic[] topic) {
		this.topic = topic;
	}

	
}
