package com.citi.quest.api.domain;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import com.citi.quest.api.dtos.QuestionDTO;

@Document
public class Application extends AbstractDocument{

	UserInfo user;
	
	Task task;
	
	QuestionDTO screeningQuestions;
	
	LocalDateTime startDate;
	
	LocalDateTime endDate;
	
	String commentsOrNotes;

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

	public QuestionDTO getScreeningQuestions() {
		return screeningQuestions;
	}

	public void setScreeningQuestions(QuestionDTO screeningQuestions) {
		this.screeningQuestions = screeningQuestions;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public String getCommentsOrNotes() {
		return commentsOrNotes;
	}

	public void setCommentsOrNotes(String commentsOrNotes) {
		this.commentsOrNotes = commentsOrNotes;
	}


}