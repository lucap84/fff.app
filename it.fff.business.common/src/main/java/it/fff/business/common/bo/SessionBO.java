package it.fff.business.common.bo;

public class SessionBO implements BusinessObject {

	private int	id;
	private AccountBO account;
	private String deviceId;
	private String sharedKey;
	private boolean isValidSession;
	
	
	public boolean isValidSession() {
		return isValidSession;
	}
	public void setValidSession(boolean isValidSession) {
		this.isValidSession = isValidSession;
	}
	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public AccountBO getAccount() {
		return account;
	}
	public void setAccount(AccountBO account) {
		this.account = account;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getSharedKey() {
		return sharedKey;
	}
	public void setSharedKey(String sharedKey) {
		this.sharedKey = sharedKey;
	}
	
	
}
