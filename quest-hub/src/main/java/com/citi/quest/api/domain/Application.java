package com.citi.quest.api.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.citi.quest.api.dtos.QuestionDTO;

@Document
public class Application extends AbstractDocument {

	UserInfo user;

	Task task;

	List<QuestionDTO> screeningQuestions;

	Date startDate;

	Date endDate;

	String commentsOrNotes;

	/**
	 * @return the user
	 */
	public UserInfo getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
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
	 * @param task
	 *            the task to set
	 */
	public void setTask(Task task) {
		this.task = task;
	}

	public List<QuestionDTO> getScreeningQuestions() {
		return screeningQuestions;
	}

	public void setScreeningQuestions(List<QuestionDTO> screeningQuestions) {
		this.screeningQuestions = screeningQuestions;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCommentsOrNotes() {
		return commentsOrNotes;
	}

	public void setCommentsOrNotes(String commentsOrNotes) {
		this.commentsOrNotes = commentsOrNotes;
	}

}