package it.fff.business.common.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sessione")
public class SessionEO extends EntityObject {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer	id;
	
	@Column(name = "Device")
	private String deviceId;
	
	@Column(name = "SharedKey")
	private String sharedKey;
	
	@Column(name = "Flg_Logged")
	private boolean isLogged;
	
	@Column(name = "Data_Login")
	private String dataLogin;
	
	@Column(name = "Data_Logout")
	private String dataLogout;
		
	@ManyToOne
	@JoinColumn(name = "Account_ID", nullable = false)
	private AccountEO account;
	
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public AccountEO getAccount() {
		return account;
	}
	public void setAccount(AccountEO account) {
		this.account = account;
	}
	public boolean isLogged() {
		return isLogged;
	}
	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
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
	
	/*
	 * setter if not empty
	 */
	public void setIdIfNotEmpty(Integer id) {
		if(!isEmpty(id)) this.id = id;
	}
	public void setDeviceIdIfNotEmpty(String deviceId) {
		if(!isEmpty(deviceId)) this.deviceId = deviceId;
	}
	public void setSharedKeyIfNotEmpty(String sharedKey) {
		if(!isEmpty(sharedKey)) this.sharedKey = sharedKey;
	}
	public void setDataLoginIfNotEmpty(String dataLogin) {
		if(!isEmpty(dataLogin)) this.dataLogin = dataLogin;
	}

	public void setDataLogoutIfNotEmpty(String dataLogout) {
		if(!isEmpty(dataLogout)) this.dataLogout = dataLogout;
	}	
	
}
