package com.citi.quest.api.dtos;

public class SearchTaskDTO {

	private String createdBy;
	
	private int pageNumber;
	
	private int pageSize;
	
	private String sortOrder;
	
	private String sortBy;
	
	private String search;
	
	private Long tasktopicId;
	
	private String skillId;
	
	private Integer tasktypeId;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Long getTasktopicId() {
		return tasktopicId;
	}
	

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public void setTasktopicId(Long tasktopicId) {
		this.tasktopicId = tasktopicId;
	}

	public String getSkillId() {
		return skillId;
	}

	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}

	public Integer getTasktypeId() {
		return tasktypeId;
	}

	public void setTasktypeId(Integer tasktypeId) {
		this.tasktypeId = tasktypeId;
	}
	
	
	
}
