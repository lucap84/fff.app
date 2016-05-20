package it.fff.business.common.bo;

import java.util.ArrayList;
import java.util.List;

public class AccountBO implements BusinessObject {
	
	private int	id;
	private long facebookId;
	private String email;
	private String password;
	private String verificationCode;
	private boolean flgValidita;
	private boolean flgVerificato;
	private int userId;
	private List<SessionBO> sessions;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<SessionBO> getSessions() {
		if(sessions==null){
			sessions = new ArrayList<SessionBO>(); 
		}
		return sessions;
	}
	public void setSessions(List<SessionBO> sessions) {
		this.sessions = sessions;
	}
	public long getFacebookId() {
		return facebookId;
	}
	public void setFacebookId(long facebookId) {
		this.facebookId = facebookId;
	}
	
}
