package it.fff.integration.facade.exception;

import it.fff.business.common.exception.ApplicationException;

public class PersistenceException extends ApplicationException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5522704529299518875L;

	public PersistenceException(String message, Exception innerException){
		super(message,innerException);
	}	

}
