package com.citi.quest.api.dtos;

import java.util.List;

import com.citi.quest.api.domain.Task;

public class DashboardDTO {
	
	List<Task> myDrafts;
	List<Task> myPosts;
	List<Task> myTemplates;
	List<Task> myRecomendedTasks;
	
	
	public List<Task> getMyDrafts() {
		return myDrafts;
	}
	public void setMyDrafts(List<Task> myDrafts) {
		this.myDrafts = myDrafts;
	}
	public List<Task> getMyPosts() {
		return myPosts;
	}
	public void setMyPosts(List<Task> myPosts) {
		this.myPosts = myPosts;
	}
	public List<Task> getMyTemplates() {
		return myTemplates;
	}
	public void setMyTemplates(List<Task> myTemplates) {
		this.myTemplates = myTemplates;
	}
	public List<Task> getMyRecomandedTasks() {
		return myRecomendedTasks;
	}
	public void setMyRecomandedTasks(List<Task> myRecomandedTasks) {
		this.myRecomendedTasks = myRecomandedTasks;
	}
	
	
}
