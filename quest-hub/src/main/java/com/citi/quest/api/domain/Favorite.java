package com.citi.quest.api.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Favorite{
	
	@Id
	private String userId;
	
	private List<Long> taskIds;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Long> getTaskIds() {
		return taskIds;
	}

	public void setTaskIds(Long taskId) {
		if(null == this.taskIds) taskIds= new ArrayList<>();
		taskIds.add(taskId);
	}

}
