package it.fff.integration.facade.exception;

import it.fff.business.common.exception.ApplicationException;

public class IntegrationException extends ApplicationException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5522704529299518875L;

	public IntegrationException(String message){
		super(message, new Exception());
	}
	
	public IntegrationException(String message, Exception innerException){
		super(message,innerException);
	}	

}
