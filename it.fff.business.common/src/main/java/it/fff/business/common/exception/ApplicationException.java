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

	public ApplicationException(String message, Exception innerException){
		super(message, innerException);
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
	
	

}
