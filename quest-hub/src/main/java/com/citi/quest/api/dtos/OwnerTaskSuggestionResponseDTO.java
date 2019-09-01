package com.citi.quest.api.dtos;

import java.util.List;

import com.citi.quest.api.domain.Topic;

public class OwnerTaskSuggestionResponseDTO {
	
	List<RelatedTaskDTO> relatedTasks;
	
	List<Topic> relatedTopics;

	public List<RelatedTaskDTO> getRelatedTasks() {
		return relatedTasks;
	}

	public void setRelatedTasks(List<RelatedTaskDTO> relatedTasks) {
		this.relatedTasks = relatedTasks;
	}

	public List<Topic> getRelatedTopics() {
		return relatedTopics;
	}

	public void setRelatedTopics(List<Topic> relatedTopics) {
		this.relatedTopics = relatedTopics;
	}
	

}
