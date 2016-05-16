package it.fff.business.common.bo;


public class SessionBO implements BusinessObject {

	private int	id;
	private AccountBO account;
	private String deviceId;
	private String sharedKey;
	private boolean isLogged;
	private String dataLogin;
	private String dataLogout;
	private String socialToken;
	private int socialTokenExpires;
	
	public boolean isLogged() {
		return isLogged;
	}
	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
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
	public String getDataLogin() {
		return dataLogin;
	}
	public void setDataLogin(String dataLogin) {
		this.dataLogin = dataLogin;
	}
	public String getDataLogout() {
		return dataLogout;
	}
	public void setDataLogout(String dataLogout) {
		this.dataLogout = dataLogout;
	}
	public String getSocialToken() {
		return socialToken;
	}
	public void setSocialToken(String socialToken) {
		this.socialToken = socialToken;
	}
	public int getSocialTokenExpires() {
		return socialTokenExpires;
	}
	public void setSocialTokenExpires(int socialTokenExpires) {
		this.socialTokenExpires = socialTokenExpires;
	}
	
}
