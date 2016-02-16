package it.fff.clientserver.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EmailInfoDTO extends DataTransferObject {
	
	private static final long serialVersionUID = 2091215225171336760L;
	
	private String email;
	private boolean isExisting;
	private boolean isValidAccount;
	private boolean isVerifiedAccount;
	
	@XmlElement public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@XmlElement public boolean isExisting() {
		return isExisting;
	}
	public void setExisting(boolean isExisting) {
		this.isExisting = isExisting;
	}
	@XmlElement public boolean isValidAccount() {
		return isValidAccount;
	}
	public void setValidAccount(boolean isValidAccount) {
		this.isValidAccount = isValidAccount;
	}
	@XmlElement public boolean isVerifiedAccount() {
		return isVerifiedAccount;
	}
	public void setVerifiedAccount(boolean isVerifiedAccount) {
		this.isVerifiedAccount = isVerifiedAccount;
	}
	


}
