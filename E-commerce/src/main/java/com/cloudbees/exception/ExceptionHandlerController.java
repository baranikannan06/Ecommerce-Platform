package com.cloudbees.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cloudbees.model.GenericResponse;
import com.cloudbees.util.Constants;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(ProductDeletionException.class)
	public ResponseEntity<GenericResponse> productDeletionException(ProductDeletionException e) {

		GenericResponse response = new GenericResponse();
		response.setCode(Constants.INTERNAL_SERVER_ERROR);
		response.setMessage(e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(InternalServerErrorException.class)
	public ResponseEntity<GenericResponse> internalServerErrorException(InternalServerErrorException e) {

		GenericResponse response = new GenericResponse();
		response.setCode(Constants.INTERNAL_SERVER_ERROR);
		response.setMessage(e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<GenericResponse> productNotFoundException(NotFoundException e) {

		GenericResponse response = new GenericResponse();
		response.setCode(Constants.NOT_FOUND);
		response.setMessage(e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ProductSavingException.class)
	public ResponseEntity<GenericResponse> productSavingException(ProductSavingException e) {

		GenericResponse response = new GenericResponse();
		response.setCode(Constants.INTERNAL_SERVER_ERROR);
		response.setMessage(e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<GenericResponse> badRequestException(BadRequestException e) {

		GenericResponse response = new GenericResponse();
		response.setCode(Constants.BAD_REQUEST);
		response.setMessage(e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<GenericResponse> unauthorizedException(UnauthorizedException e) {

		GenericResponse response = new GenericResponse();
		response.setCode(Constants.UNAUTHORIZED);
		response.setMessage(e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}

}
