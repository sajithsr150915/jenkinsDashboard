package com.jenkins.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

@ControllerAdvice
public class JenkinsServiceExceptionHandler {
	
	@ExceptionHandler(Unauthorized.class)
	public ResponseEntity<ErrorResponse> UnAuthorizedExceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.UNAUTHORIZED);
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	} 
	
	@ExceptionHandler(InternalServerError.class)
	public ResponseEntity<ErrorResponse> invalidResponseHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR);
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	} 
	
	@ExceptionHandler(NotFound.class)
	public ResponseEntity<ErrorResponse> invalidAPIHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.NOT_FOUND);
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

}
