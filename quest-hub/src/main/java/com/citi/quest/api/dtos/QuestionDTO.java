package com.citi.quest.api.dtos;

import com.citi.quest.api.domain.Question;

public class QuestionDTO {
	
	Question question;
	
	String answer;

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	

}
