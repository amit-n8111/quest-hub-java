package com.citi.quest.api.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Skills extends AbstractDocument{
	
	String name;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
