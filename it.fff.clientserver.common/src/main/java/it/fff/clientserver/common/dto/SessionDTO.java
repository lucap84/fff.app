package it.fff.clientserver.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SessionDTO extends DataTransferObject {

	private static final long serialVersionUID = 8728685619013221329L;
	
	private int	id;
	private int accountId;
	private String deviceId;
	private String sharedKey;
	private boolean isLogged;
	private String dataLogin;
	private String dataLogout;
	
	@XmlElement public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	@XmlElement public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
}
