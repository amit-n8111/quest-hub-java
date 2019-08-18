package com.citi.quest.api.dtos;

import com.citi.quest.api.domain.EnumPlaceHolder;
import com.citi.quest.api.domain.Topic;
import com.citi.quest.api.enums.RewardType;
import com.citi.quest.api.enums.TaskStatus;
import com.citi.quest.api.enums.TaskType;

public class StaticDataDTO {

	EnumPlaceHolder[] taskStatus = getStatusValues(TaskStatus.values());

	EnumPlaceHolder[] taskType = getTaskValues(TaskType.values());

	EnumPlaceHolder[] rewardType = getRewardValues(RewardType.values());

	Topic[] topic;

	private EnumPlaceHolder[] getStatusValues(TaskStatus[] values) {
		EnumPlaceHolder[] eph = new EnumPlaceHolder[values.length];
		int i = 0;
		for (TaskStatus status : values) {
			eph[i] = new EnumPlaceHolder();
			eph[i].setId(status.getId());
			eph[i].setName(status.getStatus());
			i++;
		}
		return eph;
	}

	private EnumPlaceHolder[] getRewardValues(RewardType[] values) {
		EnumPlaceHolder[] eph = new EnumPlaceHolder[values.length];
		int i = 0;
		for (RewardType status : values) {
			eph[i] = new EnumPlaceHolder();
			eph[i].setId(status.getId());
			eph[i].setName(status.getRewardType());
			i++;
		}
		return eph;
	}

	private EnumPlaceHolder[] getTaskValues(TaskType[] values) {
		EnumPlaceHolder[] eph = new EnumPlaceHolder[values.length];
		int i = 0;
		for (TaskType status : values) {
			eph[i] = new EnumPlaceHolder();
			eph[i].setId(status.getId());
			eph[i].setName(status.getTypeOfTask());
			i++;
		}
		return eph;
	}

	public EnumPlaceHolder[] getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(EnumPlaceHolder[] taskStatus) {
		this.taskStatus = taskStatus;
	}

	public EnumPlaceHolder[] getTaskType() {
		return taskType;
	}

	public void setTaskType(EnumPlaceHolder[] taskType) {
		this.taskType = taskType;
	}

	public EnumPlaceHolder[] getRewardType() {
		return rewardType;
	}

	public void setRewardType(EnumPlaceHolder[] rewardType) {
		this.rewardType = rewardType;
	}

	public Topic[] getTopic() {
		return topic;
	}

	public void setTopic(Topic[] topic) {
		this.topic = topic;
	}

}
