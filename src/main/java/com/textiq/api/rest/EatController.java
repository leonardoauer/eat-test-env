package com.textiq.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;

import com.textiq.api.json.ApiSuccess;

public class EatController {

	@Autowired
	private MessageSource messageSource;
	
	public String getSuccessMessage() {
		return messageSource.getMessage("success.message.send", null, null);
	}
	
	public String getMessage(String message) {
		return messageSource.getMessage(message, null, null);
	}
	
	public ResponseEntity<?> sendError(String errorMessage) {
		return ResponseEntity.ok(ApiSuccess.builder()
				.code(ApiCodes.ERROR_INVALID_REQUEST)
				.message(errorMessage)
				.build());
	}
	
	public ResponseEntity<?> sendSuccess(String successMessage) {
		
		return ResponseEntity.ok(ApiSuccess.builder()
				.code(ApiCodes.SUCCESS_MESSAGE)
				.message(successMessage)
				.build());
	}
	
	public <T> ResponseEntity<?> sendSuccessAndData(T data) {
		
		return ResponseEntity.ok(ApiSuccess.builder()
				.code(ApiCodes.SUCCESS_MESSAGE)
				.message("success")
				.data(data)
				.build());
	}
	
	
	public <T> ResponseEntity<?> sendSuccessAndData(String successMessage, T data) {
		
		return ResponseEntity.ok(ApiSuccess.builder()
				.code(ApiCodes.SUCCESS_MESSAGE)
				.message(successMessage)
				.data(data)
				.build());
	}
}
