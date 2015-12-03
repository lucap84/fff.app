package it.fff.clientserver.common.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;


public class AccountDTO extends DataTransferObject {

	private String id;
	private String email;
	private String password;
	private String verificationCode;
	private boolean flgValidita;
	private boolean flgVerificato;
	private UserDTO user;
	private List<SessionDTO> sessions;
	
	@XmlElement public String getId() {
		return id;
	}
	public void setId(String id) {
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
	@XmlElement public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	@XmlElement public List<SessionDTO> getSessions() {
		return sessions;
	}
	public void setSessions(List<SessionDTO> sessions) {
		this.sessions = sessions;
	}
	
}
