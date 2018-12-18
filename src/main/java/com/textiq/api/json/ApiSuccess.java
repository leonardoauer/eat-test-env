package com.textiq.api.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

/**
 * Classe envelope de respostas com sucesso na requisição
 * 
 * @author lauer
 *
 */
@Getter
@Builder
public class ApiSuccess<T> {
	
	@JsonInclude(Include.NON_EMPTY)
	private Integer code;
	
	private String message;
	
	@JsonInclude(Include.NON_EMPTY)
	private T data;
}