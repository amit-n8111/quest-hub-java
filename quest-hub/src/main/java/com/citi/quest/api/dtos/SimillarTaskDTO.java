package com.citi.quest.api.dtos;

public class SimillarTaskDTO {
	
	Long id;
	
	String taskName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public SimillarTaskDTO(Long id, String taskName) {
		super();
		this.id = id;
		this.taskName = taskName;
	}
	
	
	

}
