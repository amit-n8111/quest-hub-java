package com.citi.quest.api.dtos;

import java.util.List;

public class ApplicationDTO {

	List<String> availableDateRange;
	String commentsOrNotes;
	List<QuestionDTO> screeningQuestions;

	public List<String> getAvailableDateRange() {
		return availableDateRange;
	}

	public void setAvailableDateRange(List<String> availableDateRange) {
		this.availableDateRange = availableDateRange;
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
