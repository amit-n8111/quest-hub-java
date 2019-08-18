package com.citi.quest.api.enums;

public enum RewardType {

	GRATITUDE("gratitude", 1);

	private int id;
	private String rewardType;

	private RewardType(String rewardType, int id) {
		this.id = id;
		this.rewardType = rewardType;
	}

	public int getId() {
		return id;
	}

	public String getRewardType() {
		return rewardType;
	}

}
