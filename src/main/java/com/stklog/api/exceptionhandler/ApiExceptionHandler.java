package com.stklog.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.stklog.domain.exception.BusinessException;
import com.stklog.domain.exception.EntityNotFoundException;

import lombok.AllArgsConstructor;

@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<ApiErrorRequest.Field> fields = new ArrayList<>();
		
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String name = ((FieldError) error).getField();
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			fields.add(new ApiErrorRequest.Field(name, message));
		}
		
		ApiErrorRequest error = new ApiErrorRequest();
		error.setStatus(status.value());
		error.setDateTime(OffsetDateTime.now());
		error.setTitle("Um ou mais campos estão inválidos.");
		error.setFields(fields);

		return handleExceptionInternal(ex, ex, headers, status, request);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusiness(BusinessException ex, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		ApiErrorRequest error = new ApiErrorRequest();
		error.setStatus(status.value());
		error.setDateTime(OffsetDateTime.now());
		error.setTitle(ex.getMessage());
		
		return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, WebRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		ApiErrorRequest error = new ApiErrorRequest();
		error.setStatus(status.value());
		error.setDateTime(OffsetDateTime.now());
		error.setTitle(ex.getMessage());
		
		return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
	}
}
