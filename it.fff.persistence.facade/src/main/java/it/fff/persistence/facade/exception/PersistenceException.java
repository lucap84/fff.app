package it.fff.persistence.facade.exception;

public class PersistenceException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5522704529299518875L;

	public PersistenceException(String message, Exception innerException){
		super(message,innerException);
	}	

}
