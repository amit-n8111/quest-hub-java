package com.citi.quest.api.domain;

public class Question extends AbstractDocument {

	String questionName;

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return questionName;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.questionName = question;
	}
}
