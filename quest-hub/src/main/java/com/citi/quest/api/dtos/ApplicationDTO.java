package com.citi.quest.api.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class ApplicationDTO {

	LocalDateTime startDate;
	LocalDateTime endDate;
	String commentsOrNotes;
	List<QuestionDTO> screeningQuestions;

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

	public List<QuestionDTO> getScreeningQuestions() {
		return screeningQuestions;
	}

	public void setScreeningQuestions(List<QuestionDTO> screeningQuestions) {
		this.screeningQuestions = screeningQuestions;
	}

}
