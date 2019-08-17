package com.citi.quest.api.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.citi.quest.api.dtos.QuestionDTO;

@Document
public class Application extends AbstractDocument{

	UserInfo user;
	
	Task task;
	
	QuestionDTO screeningQuestions;
	
	List<LocalDateTime> availableDateRange;
	
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

	public List<LocalDateTime> getAvailableDateRange() {
		return availableDateRange;
	}

	public void setAvailableDateRange(List<LocalDateTime> availableDateRange) {
		this.availableDateRange = availableDateRange;
	}

	public String getCommentsOrNotes() {
		return commentsOrNotes;
	}

	public void setCommentsOrNotes(String commentsOrNotes) {
		this.commentsOrNotes = commentsOrNotes;
	}


}