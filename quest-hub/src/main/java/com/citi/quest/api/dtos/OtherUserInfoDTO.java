package com.citi.quest.api.dtos;

import java.util.Date;

public class OtherUserInfoDTO {

	String soeId;

	String name;

	Float score;

	Double rating;

	Date avalableStartDate;

	Date avilableEndDate;

	public Date getAvalableStartDate() {
		return avalableStartDate;
	}

	public void setAvalableStartDate(Date avalableStartDate) {
		this.avalableStartDate = avalableStartDate;
	}

	public Date getAvilableEndDate() {
		return avilableEndDate;
	}

	public void setAvilableEndDate(Date avilableEndDate) {
		this.avilableEndDate = avilableEndDate;
	}

	public String getSoeId() {
		return soeId;
	}

	public void setSoeId(String soeId) {
		this.soeId = soeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

}
