package com.textiq.api.mongo.model.enums;

import com.textiq.api.exceptions.EatException;

public enum SetType {

	ENTITY, DOCUMENT;

	public static SetType getByString(String value) {

		if(ENTITY.name().equalsIgnoreCase(value)) {
			return ENTITY;
		} else if(DOCUMENT.name().equalsIgnoreCase(value)) {
			return DOCUMENT;
		}
		
		throw new EatException("### " + value + " is not a valid type value ###");
	}
	
	public String getName() {
		return this.name().toLowerCase();
	}
}
