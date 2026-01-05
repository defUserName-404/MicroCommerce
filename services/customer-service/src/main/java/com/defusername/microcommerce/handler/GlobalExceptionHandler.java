package com.defusername.microcommerce.handler;

import com.defusername.microcommerce.exception.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<String> handle(CustomerNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
							 .body(exception.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exception) {
		final Map<String, String> errors = new HashMap<>();
		exception.getBindingResult()
				 .getAllErrors()
				 .forEach(error -> {
					 final String fieldName = ((FieldError) error).getField();
					 final String errorMessage = error.getDefaultMessage();
					 errors.put(fieldName, errorMessage);
				 });
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							 .body(ErrorResponse.builder()
												.errors(errors)
												.build());
	}

}
