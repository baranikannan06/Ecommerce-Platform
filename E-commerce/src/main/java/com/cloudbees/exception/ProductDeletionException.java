package com.cloudbees.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ProductDeletionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProductDeletionException(String message) {
		super(message);
	}

}
