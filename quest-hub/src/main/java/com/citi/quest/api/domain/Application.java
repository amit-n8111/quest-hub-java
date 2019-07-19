package com.citi.quest.api.domain;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Application extends AbstractDocument{

	String applicationId;
	
	UserInfo user;
	
	Task task;
	
	Map<Questions, String> response;

	/**
	 * @return the applicationId
	 */
	public String getApplicationId() {
		return applicationId;
	}

	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	/**
	 * @return the user
	 */
	public UserInfo getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserInfo user) {
		this.user = user;
	}

	/**
	 * @return the task
	 */
	public Task getTask() {
		return task;
	}

	/**
	 * @param task the task to set
	 */
	public void setTask(Task task) {
		this.task = task;
	}

	/**
	 * @return the response
	 */
	public Map<Questions, String> getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(Map<Questions, String> response) {
		this.response = response;
	}
}
