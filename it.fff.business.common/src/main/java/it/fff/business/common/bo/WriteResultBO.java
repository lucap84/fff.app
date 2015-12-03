package it.fff.business.common.bo;

import java.util.List;

public class WriteResultBO implements BusinessObject {
	
	private boolean success;
	private int writtenKey;
	private int affectedRecords;
	private List<String> errorCodes;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getWrittenKey() {
		return writtenKey;
	}
	public void setWrittenKey(int writtenKey) {
		this.writtenKey = writtenKey;
	}
	public int getAffectedRecords() {
		return affectedRecords;
	}
	public void setAffectedRecords(int affectedRecords) {
		this.affectedRecords = affectedRecords;
	}
	public List<String> getErrorCodes() {
		return errorCodes;
	}
	public void setErrorCodes(List<String> errorCodes) {
		this.errorCodes = errorCodes;
	}
	
}
