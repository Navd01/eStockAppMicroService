package com.naveed.exceptions;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import feign.FeignException;

@RestControllerAdvice
public class FeignHandler {
	
	@ExceptionHandler(FeignException.class)
	public ResponseEntity<Object> handleFeignStatusException(FeignException e, HttpServletResponse response) {
		String error = fetchErrorFromFeign(e);
		StockExceptionResponse exceptionResponse = new StockExceptionResponse(error);
		return new ResponseEntity(exceptionResponse , HttpStatus.BAD_REQUEST);
    }

	private String fetchErrorFromFeign(FeignException e) {
		String[] e1 = e.getMessage().split(":");
		String error = e1[e1.length-1];
		error = error.substring(1, error.length()-3);
		return error;
	}
}

