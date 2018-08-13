package com.ranking.controller;

import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ranking.model.CustomError;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

   @Override
   protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
       String error = "Malformed JSON request";
       return buildResponseEntity(new CustomError(HttpStatus.BAD_REQUEST, error, ex));
   }
   
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomError customError = new CustomError(HttpStatus.BAD_REQUEST);
		customError.setMessage("Validation error");
		customError.addValidationErrors(ex.getBindingResult().getFieldErrors());
		customError.addValidationError(ex.getBindingResult().getGlobalErrors());
		return buildResponseEntity(customError);
	}
   
   private ResponseEntity<Object> buildResponseEntity(CustomError customError) {
       return new ResponseEntity<>(customError, customError.getStatus());
   }

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
		CustomError customError = new CustomError(HttpStatus.NOT_FOUND);
		customError.setMessage(ex.getMessage());
		return buildResponseEntity(customError);
	}
	
		
	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
		if (ex.getCause() instanceof ConstraintViolationException) {
			return buildResponseEntity(new CustomError(HttpStatus.CONFLICT, "Database error", ex.getCause()));
		}
		return buildResponseEntity(new CustomError(HttpStatus.INTERNAL_SERVER_ERROR, ex));
	}
	
	
	@ExceptionHandler(NullPointerException.class)
	protected ResponseEntity<Object> handleNullPointer(NullPointerException ex) {
		CustomError customError = new CustomError(HttpStatus.BAD_REQUEST);
		customError.setMessage(ex.getMessage());
		return buildResponseEntity(customError);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	protected ResponseEntity<Object> handleNoSuchElement(NoSuchElementException ex) {
		CustomError customError = new CustomError(HttpStatus.BAD_REQUEST);
		customError.setMessage(ex.getMessage());
		return buildResponseEntity(customError);
	}


}