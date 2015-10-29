package it.fff.business.facade.exception;

public class BusinessException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3634500334533401305L;

	public BusinessException(String message, Exception innerException){
		super(message,innerException);
	}

}
