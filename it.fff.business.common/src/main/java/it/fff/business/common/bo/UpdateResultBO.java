package it.fff.business.common.bo;

import java.util.List;

public class UpdateResultBO implements BusinessObject{
	
	private boolean success;
	private int updatedKey;
	private int numRecordsUpdated;
	private List<String> errorCodes;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getUpdatedKey() {
		return updatedKey;
	}
	public void setUpdatedKey(int updatedKey) {
		this.updatedKey = updatedKey;
	}
	public int getNumRecordsUpdated() {
		return numRecordsUpdated;
	}
	public void setNumRecordsUpdated(int numRecordsUpdated) {
		this.numRecordsUpdated = numRecordsUpdated;
	}
	public List<String> getErrorCodes() {
		return errorCodes;
	}
	public void setErrorCodes(List<String> errorCodes) {
		this.errorCodes = errorCodes;
	}

	
}
