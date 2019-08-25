package com.citi.quest.api.dtos;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

public class ApplicationDTO {
	@Id
	Long id;

	Date startDate;
	Date endDate;
	String commentsOrNotes;
	List<QuestionDTO> screeningQuestions;

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

	public List<QuestionDTO> getScreeningQuestions() {
		return screeningQuestions;
	}

	public void setScreeningQuestions(List<QuestionDTO> screeningQuestions) {
		this.screeningQuestions = screeningQuestions;
	}

}
