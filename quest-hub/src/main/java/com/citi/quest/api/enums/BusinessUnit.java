package com.citi.quest.api.enums;

public enum BusinessUnit {
	ETS("Enterprise Technology Solutions", "ETS");
	private String buFullName;
	private String buShortName;
	
	private BusinessUnit(String buFullName, String buShortName) {
		this.buFullName = buFullName;
		this.buShortName = buShortName;
	}
	
	public String getBuFullName() {
		return buFullName;
	}
	
	public String getBuShortName() {
		return buShortName;
	}
}
