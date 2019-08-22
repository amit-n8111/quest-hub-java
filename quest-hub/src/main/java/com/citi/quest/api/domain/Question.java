package com.citi.quest.api.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Question extends AbstractDocument {

	String question;

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
}
