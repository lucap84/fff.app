package it.fff.business.common.bo;

import java.util.List;

public class CreateResultBO implements BusinessObject{
	
	private boolean success;
	private int createdKey;
	private int numRecordsCreated;
	private List<String> errorCodes;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getCreatedKey() {
		return createdKey;
	}
	public void setCreatedKey(int createdKey) {
		this.createdKey = createdKey;
	}
	public int getNumRecordsCreated() {
		return numRecordsCreated;
	}
	public void setNumRecordsCreated(int numRecordsCreated) {
		this.numRecordsCreated = numRecordsCreated;
	}
	public List<String> getErrorCodes() {
		return errorCodes;
	}
	public void setErrorCodes(List<String> errorCodes) {
		this.errorCodes = errorCodes;
	}
	
}
