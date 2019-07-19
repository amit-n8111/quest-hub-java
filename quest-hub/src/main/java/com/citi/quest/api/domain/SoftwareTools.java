package com.citi.quest.api.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SoftwareTools  extends AbstractDocument{
	
	String toolId;
	
	String toolName;
	
	String version;
	
	Topic topic;

	/**
	 * @return the toolId
	 */
	public String getToolId() {
		return toolId;
	}

	/**
	 * @param toolId the toolId to set
	 */
	public void setToolId(String toolId) {
		this.toolId = toolId;
	}

	/**
	 * @return the toolName
	 */
	public String getToolName() {
		return toolName;
	}

	/**
	 * @param toolName the toolName to set
	 */
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the topic
	 */
	public Topic getTopic() {
		return topic;
	}

	/**
	 * @param topic the topic to set
	 */
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
}
