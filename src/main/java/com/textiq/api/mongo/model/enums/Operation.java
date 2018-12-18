package com.textiq.api.mongo.model.enums;

import com.textiq.api.exceptions.EatException;

public enum Operation {

	JOIN, INTERSECT, DIFFERENCE, FILTER, GROUPBY;

	public static Operation getByString(String value) {

		for (Operation op : values()) {
			if(op.name().equalsIgnoreCase(value)) {
				return op;
			}
		}
		
		throw new EatException("### " + value + " is not a valid operation value ###");
	}
	
	public String getName() {
		return this.name().toLowerCase();
	}
}
