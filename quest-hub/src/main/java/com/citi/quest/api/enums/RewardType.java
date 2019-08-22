package com.citi.quest.api.enums;

public enum RewardType {

	GRATITUDE(1, "gratitude");

	private Integer id;
	private String rewardType;

	private RewardType(Integer id, String rewardType) {
		this.id = id;
		this.rewardType = rewardType;
	}

	public Integer getId() {
		return id;
	}

	public String getRewardType() {
		return rewardType;
	}

}
