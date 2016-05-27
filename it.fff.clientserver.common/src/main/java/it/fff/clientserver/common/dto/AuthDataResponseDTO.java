package it.fff.clientserver.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthDataResponseDTO extends DataTransferObject {
	
	private static final long serialVersionUID = -7260208612326225230L;
	private int userId;
	private String socialId;
	private String socialToken;
	private int socialTokenExpires;
	private String serverPublicKey;
	
	@XmlElement public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@XmlElement public String getServerPublicKey() {
		return serverPublicKey;
	}
	public void setServerPublicKey(String serverPublicKey) {
		this.serverPublicKey = serverPublicKey;
	}
	@XmlElement public String getSocialToken() {
		return socialToken;
	}
	public void setSocialToken(String socialToken) {
		this.socialToken = socialToken;
	}
	@XmlElement public String getSocialId() {
		return socialId;
	}
	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}
	@XmlElement public int getSocialTokenExpires() {
		return socialTokenExpires;
	}
	public void setSocialTokenExpires(int socialTokenExpires) {
		this.socialTokenExpires = socialTokenExpires;
	}

	
}
