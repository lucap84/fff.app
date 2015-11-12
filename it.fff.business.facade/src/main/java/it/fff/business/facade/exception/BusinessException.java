package it.fff.business.facade.exception;

import it.fff.business.common.exception.ApplicationException;
import it.fff.business.common.util.ErrorCodes;

public class BusinessException extends ApplicationException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3634500334533401305L;

	public BusinessException(String message, Exception innerException){
		super(message,innerException);
	}
	
	public static void manageException(ApplicationException e, String errorCode) throws BusinessException{

		BusinessException businessException = new BusinessException(e.getMessage(),e);
		businessException.addErrorCodes(e.getErrorCodes());
		businessException.addErrorCode(ErrorCodes.ERR_BUSIN_GETEVENT);
		throw businessException;		
	}
}
