package com.citi.quest.api.domain;

public class Questions extends AbstractDocument  {
	
	
	
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
