package it.fff.business.common.bo;

import java.util.List;

public class AccountBO implements BusinessObject {
	
	private int id;
	private String email;
	private String password;
	private int verificationCode;
	private boolean flgValidita;
	private boolean flgVerificato;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private List<SessionBO> sessions;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(int verificationCode) {
		this.verificationCode = verificationCode;
	}
	public boolean isFlgValidita() {
		return flgValidita;
	}
	public void setFlgValidita(boolean flgValidita) {
		this.flgValidita = flgValidita;
	}
	public boolean isFlgVerificato() {
		return flgVerificato;
	}
	public void setFlgVerificato(boolean flgVerificato) {
		this.flgVerificato = flgVerificato;
	}
	public List<SessionBO> getSessions() {
		return sessions;
	}
	public void setSessions(List<SessionBO> sessions) {
		this.sessions = sessions;
	}
	
}
