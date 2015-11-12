package it.fff.business.common.bo;

import java.util.List;

public class DeleteResultBO implements BusinessObject{
	
	private boolean success;
	private int deletedKey;
	private int numRecordsDeleted;
	private List<String> errorCodes;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getDeletedKey() {
		return deletedKey;
	}
	public void setDeletedKey(int deletedKey) {
		this.deletedKey = deletedKey;
	}
	public int getNumRecordsDeleted() {
		return numRecordsDeleted;
	}
	public void setNumRecordsDeleted(int numRecordsDeleted) {
		this.numRecordsDeleted = numRecordsDeleted;
	}
	public List<String> getErrorCodes() {
		return errorCodes;
	}
	public void setErrorCodes(List<String> errorCodes) {
		this.errorCodes = errorCodes;
	}
	
	

}
