package com.citi.quest.api.service.impl;

import org.springframework.stereotype.Component;

@Component
public class RequestScopeBean {
	
	float maxUserScroeForTask;

	public float getMaxUserScroeForTask() {
		return maxUserScroeForTask;
	}

	public void setMaxUserScroeForTask(float maxUserScroeForTask) {
		this.maxUserScroeForTask = maxUserScroeForTask;
	}
	
	

}
