package it.fff.business.common.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "session")
public class SessionEO implements EntityObject {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer	id;
	
	@ManyToOne
	@JoinColumn(name = "Account_ID", nullable = false)
	private AccountEO account;
	
	@Column(name = "Device")
	private String deviceId;
	
	@Column(name = "SharedKey")
	private String sharedKey;
	
	@Column(name = "Flg_Validita")
	private boolean isValidSession;
		
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
	public boolean isValidSession() {
		return isValidSession;
	}
	public void setValidSession(boolean isValidSession) {
		this.isValidSession = isValidSession;
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
}
