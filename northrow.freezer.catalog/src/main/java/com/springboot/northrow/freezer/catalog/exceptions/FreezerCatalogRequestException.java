package com.springboot.northrow.freezer.catalog.exceptions;

public class FreezerCatalogRequestException extends RuntimeException {

	/**
	 * @param message
	 * @param cause
	 */
	public FreezerCatalogRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public FreezerCatalogRequestException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public FreezerCatalogRequestException(Throwable cause) {
		super(cause);
	}

}
