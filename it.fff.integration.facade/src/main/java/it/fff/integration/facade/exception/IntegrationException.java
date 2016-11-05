package it.fff.integration.facade.exception;

import it.fff.business.common.exception.ApplicationException;

public class IntegrationException extends ApplicationException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5522704529299518875L;

	public IntegrationException(String message){
		super(message);
	}
	
	public IntegrationException(String message, Exception innerException){
		super(message,innerException);
	}

	public static void manageException(ApplicationException e, String errorCode) throws IntegrationException{
		IntegrationException ex = new IntegrationException(e.getMessage(), e);
		ex.addErrorCodes(e.getErrorCodes());
		ex.addErrorCode(errorCode);
		throw ex;
	}

}
