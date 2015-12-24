package it.fff.clientserver.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import it.fff.clientserver.common.enums.UserSexEnum;

@XmlRootElement
public class RegistrationInputDTO extends DataTransferObject {

	private static final long serialVersionUID = 5474778690001259111L;
	
	private String email;
	private String encodedPassword;
	private String nome;
	private String cognome;
	private String dataNascita;
	private UserSexEnum sesso;
	private String sharedKey;
	private String deviceId;
	
	@XmlElement public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@XmlElement public String getEncodedPassword() {
		return encodedPassword;
	}
	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}
	@XmlElement public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@XmlElement public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	@XmlElement public String getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}
	@XmlElement public UserSexEnum getSesso() {
		return sesso;
	}
	public void setSesso(UserSexEnum sesso) {
		this.sesso = sesso;
	}
	public String getSharedKey() {
		return sharedKey;
	}
	public void setSharedKey(String sharedKey) {
		this.sharedKey = sharedKey;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
}
