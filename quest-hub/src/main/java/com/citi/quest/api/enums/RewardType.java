package com.citi.quest.api.enums;

public enum RewardType {
	
	GRATITUDE("gratitude");
	
	private String rewardType;
	
	private RewardType(String rewardType) {
		this.rewardType = rewardType;
	}
	
	public String getStatus() {
		return rewardType;
	}

}
