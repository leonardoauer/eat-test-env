package com.textiq.api.exceptions;

public class EatException extends RuntimeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	public EatException(String errorMessage) {
		super(errorMessage);
	}
}
