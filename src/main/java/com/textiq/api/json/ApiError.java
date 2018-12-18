package com.textiq.api.json;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

/**
 * Envelope class of common API errors
 * 
 * @author lauer
 *
 */
@Getter
@Builder
public class ApiError {
	
	@JsonInclude(Include.NON_EMPTY)
	private Integer code;
	
	private String message;
	
	@JsonInclude(Include.NON_EMPTY)
	private String fieldName;
	
	@JsonInclude(Include.NON_EMPTY)
	private List<ApiError> errors;

	public ApiError addError(Integer code, String message) {
		
		if(errors == null) {
			errors = Collections.emptyList();
		}
		
		errors.add(ApiError.builder()
							.code(code)
							.message(message).build());
		
		return this;
	}
}