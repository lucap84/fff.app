package it.fff.clientserver.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SessionDTO extends DataTransferObject {

	private String	id;
	private AccountDTO account;
	private String deviceId;
	private String sharedKey;
	private boolean isLogged;
	private String dataLogin;
	private String dataLogout;
	
	@XmlElement public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@XmlElement public AccountDTO getAccount() {
		return account;
	}
	public void setAccount(AccountDTO account) {
		this.account = account;
	}
	@XmlElement public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	@XmlElement public String getSharedKey() {
		return sharedKey;
	}
	public void setSharedKey(String sharedKey) {
		this.sharedKey = sharedKey;
	}
	@XmlElement public boolean isLogged() {
		return isLogged;
	}
	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}
	@XmlElement public String getDataLogin() {
		return dataLogin;
	}
	public void setDataLogin(String dataLogin) {
		this.dataLogin = dataLogin;
	}
	@XmlElement public String getDataLogout() {
		return dataLogout;
	}
	public void setDataLogout(String dataLogout) {
		this.dataLogout = dataLogout;
	}
	
	
}
