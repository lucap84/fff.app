package it.fff.business.common.eo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class AccountEO implements EntityObject {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer	id;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "Verification_Code")
	private int verificationCode;
	
	@Column(name = "Flg_Validita")
	private boolean flgValidita;
	
	@Column(name = "Flg_Verificato")
	private boolean flgVerificato;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private UserEO user;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL)
	private List<SessionEO> sessions;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
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


	public UserEO getUser() {
		return user;
	}


	public void setUser(UserEO user) {
		this.user = user;
	}


	public List<SessionEO> getSessions() {
		return sessions;
	}


	public void setSessions(List<SessionEO> sessions) {
		this.sessions = sessions;
	}	
	
	

}
