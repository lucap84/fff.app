package it.fff.business.common.exception;

import java.util.ArrayList;
import java.util.List;

public class ApplicationException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> errorCodes;
	
	public ApplicationException(){
		super();
		this.errorCodes = new ArrayList<String>();
	}

	public ApplicationException(String errMsg){
		super(errMsg);
		this.errorCodes = new ArrayList<String>();
	}

	public ApplicationException(Exception originalException){
		super(originalException);
		this.errorCodes = new ArrayList<String>();
	}
	

	public ApplicationException(String message, Exception originalException){
		super(message, originalException);
		this.errorCodes = new ArrayList<String>();
	}
	
	public void addErrorCode(String errorCode){
		this.errorCodes.add(errorCode);
	}
	
	public void addErrorCodes(List<String> errorCodes){
		this.errorCodes.addAll(errorCodes);
	}	

	public List<String> getErrorCodes() {
		return errorCodes;
	}

	public void setErrorCodes(List<String> errorCodes) {
		this.errorCodes = errorCodes;
	}

	public static void manageException(Exception e, String errorCode) throws ApplicationException{
		ApplicationException ex = new ApplicationException(e.getMessage(), e);
		ex.addErrorCode(errorCode);
		throw ex;
	}

}
