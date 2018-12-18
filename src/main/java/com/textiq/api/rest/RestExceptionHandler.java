package com.textiq.api.rest;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.util.NestedServletException;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.textiq.api.exceptions.EatException;
import com.textiq.api.json.ApiError;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

	private static final HttpHeaders DEFAULT_HTTP_HEADER;

	private final MessageSource messageSource;
	
	static {
		DEFAULT_HTTP_HEADER = new HttpHeaders();
		DEFAULT_HTTP_HEADER.setContentType(MediaType.APPLICATION_JSON_UTF8);
	}
	   
	@ExceptionHandler({
		MissingPathVariableException.class,
		MissingServletRequestParameterException.class,
		MissingServletRequestParameterException.class,
		ServletRequestBindingException.class,
		ConversionNotSupportedException.class,
		TypeMismatchException.class,
		MethodArgumentNotValidException.class,
		MissingServletRequestPartException.class,
		HttpRequestMethodNotSupportedException.class,
		HttpMediaTypeNotSupportedException.class, 
		HttpMessageNotReadableException.class,
		HttpMediaTypeNotAcceptableException.class,
		NestedServletException.class,
		UnsupportedOperationException.class, 
		BindException.class,
		IllegalStateException.class,
		SocketTimeoutException.class, 
		EatException.class
	})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ApiError> processValidationError(Exception ex) {
		
		int errorCode= ApiCodes.ERROR_INVALID_REQUEST;
		String messageError = messageSource.getMessage("error.invalid.request", null, null);
		
		log.error(messageError, ex);
		
		List<FieldError> fieldErrors = null;
		List<ApiError> errors = null;
		
		
		if (ex instanceof BindException ) {
			
			BindException bindException = (BindException) ex;
			
			BindingResult result = bindException.getBindingResult();
			fieldErrors = result.getFieldErrors();
		}
		
		if (ex instanceof MethodArgumentNotValidException) {
			
			MethodArgumentNotValidException bindException = (MethodArgumentNotValidException) ex;
			
			BindingResult result = bindException.getBindingResult();
			fieldErrors = result.getFieldErrors();
		}
		
		if(ex instanceof HttpMessageNotReadableException) {
			//TODO: Melhorar isso. No pior caso converter de LocalDate para String e tratar no provider
			if(ex.getCause() instanceof InvalidFormatException) {
				InvalidFormatException ife = (InvalidFormatException) ex.getCause();

				fieldErrors = new ArrayList<>();
				for(Reference reference: ife.getPath()) {
					fieldErrors.add( new FieldError("json",reference.getFieldName(), ife.getMessage()));
				}
				
			} 
		}
		
		if(!CollectionUtils.isEmpty(fieldErrors)){ 
			
			//errorCode = ApiCodes.ERROR_DATA_VALIDATION;
			errors = new ArrayList<>();
			
			for (FieldError fieldError: fieldErrors) {
				errors.add(
						ApiError.builder()
						.fieldName(fieldError.getField())
						.message(messageSource.getMessage(fieldError.getCode(), 
								null, fieldError.getDefaultMessage(), null)).build());
			} 
		}
		
		if(ex instanceof EatException) {
			messageError = ex.getMessage();
		}

		return ResponseEntity.badRequest().body(ApiError.builder()
				.code(errorCode)
				.message(messageError)
				.errors(errors).build());
    }
 
	
//    @ExceptionHandler({ CustomException.class })
//	@ResponseBody
//	public ResponseEntity<ApiError> handleCustomException(CustomException ex, WebRequest request) {
//
//		String messageLogError =  messageSource.getMessage(ex.getMessageKey(), ex.getArgs(), null, null);
//
//		log.error("{} - Error: {}", messageLogError, ex.getMessage());
//
//		return ResponseEntity.badRequest().headers(DEFAULT_HTTP_HEADER).body(
//				ApiError.builder()
//				.code(ex.getCode())
//				.message(messageLogError).build()
//				);
//	}
	 
}