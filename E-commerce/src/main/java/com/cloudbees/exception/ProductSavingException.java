package com.cloudbees.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ProductSavingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProductSavingException(String message) {
		super(message);
	}
}
