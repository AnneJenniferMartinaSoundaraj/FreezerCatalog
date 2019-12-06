package com.springboot.northrow.freezer.catalog.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class FrrezerCatalogExceptionHandler {

	/**
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = { FreezerCatalogRequestException.class })
	public ResponseEntity<Object> handleFreezerCatalogRequestException(
			FreezerCatalogRequestException e, WebRequest request) {

		FreezerCatalogException exception = new FreezerCatalogException(
				new Date(), HttpStatus.BAD_REQUEST.toString(), e.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> freezerCatalogExceptionHandler(Exception ex,
			WebRequest request) {
		FreezerCatalogException exception = new FreezerCatalogException(
				new Date(), HttpStatus.INTERNAL_SERVER_ERROR.toString(),
				ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
