package it.fff.integration.facade.exception;

import it.fff.business.common.exception.ApplicationException;

public class ExternalServiceException extends ApplicationException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5522704529299518875L;

	public ExternalServiceException(String message, Exception innerException){
		super(message,innerException);
	}	

}
