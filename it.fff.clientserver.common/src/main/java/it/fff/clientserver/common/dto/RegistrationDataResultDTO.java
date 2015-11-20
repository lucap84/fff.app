package it.fff.clientserver.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RegistrationDataResultDTO extends DataTransferObject {
	
	private static final long serialVersionUID = -7260208612326225230L;
	private String userId;
	private String serverPublicKey;
	
	@XmlElement public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@XmlElement public String getServerPublicKey() {
		return serverPublicKey;
	}
	public void setServerPublicKey(String serverPublicKey) {
		this.serverPublicKey = serverPublicKey;
	}

	
}
