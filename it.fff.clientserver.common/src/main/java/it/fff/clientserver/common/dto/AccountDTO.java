package it.fff.clientserver.common.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AccountDTO extends DataTransferObject {

	private static final long serialVersionUID = -71986174187350978L;
	private int id;
	private long socialId;
	private String email;
	private String password;
	private String verificationCode;
	private boolean flgValidita;
	private boolean flgVerificato;
	private int userId;
	private List<SessionDTO> sessions;
	
	@XmlElement public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@XmlElement public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@XmlElement public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@XmlElement public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	@XmlElement public boolean isFlgValidita() {
		return flgValidita;
	}
	public void setFlgValidita(boolean flgValidita) {
		this.flgValidita = flgValidita;
	}
	@XmlElement public boolean isFlgVerificato() {
		return flgVerificato;
	}
	public void setFlgVerificato(boolean flgVerificato) {
		this.flgVerificato = flgVerificato;
	}
	@XmlElement public List<SessionDTO> getSessions() {
		return sessions;
	}
	public void setSessions(List<SessionDTO> sessions) {
		this.sessions = sessions;
	}
	@XmlElement public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@XmlElement public long getSocialId() {
		return socialId;
	}
	public void setSocialId(long socialId) {
		this.socialId = socialId;
	}
	
}
