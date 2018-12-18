package com.textiq.api.mongo.model.enums;

import com.textiq.api.exceptions.EatException;

public enum SetOwnerType {

	USER, TEAM;

	public static SetOwnerType getByString(String value) {

		if(USER.name().equalsIgnoreCase(value)) {
			return USER;
		} else if(TEAM.name().equalsIgnoreCase(value)) {
			return TEAM;
		}

		throw new EatException("### " + value + " is not a valid Owner type value ###");
	}
	
	public String getName() {
		return this.name().toLowerCase();
	}
}
