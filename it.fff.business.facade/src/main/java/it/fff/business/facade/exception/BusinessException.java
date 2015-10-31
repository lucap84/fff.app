package it.fff.business.facade.exception;

import it.fff.business.common.exception.ApplicationException;

public class BusinessException extends ApplicationException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3634500334533401305L;

	public BusinessException(String message, Exception innerException){
		super(message,innerException);
	}

}
