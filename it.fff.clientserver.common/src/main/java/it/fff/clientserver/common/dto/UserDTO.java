package it.fff.clientserver.common.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement
public class UserDTO extends DataTransferObject {
	
	private static final long serialVersionUID = -1979200792665108748L;
	private String id;
	private String email;
	private String nome;
	private String cognome;
	private String dataNascita;
	private String sesso;
	private NationDTO nazionalita;
	private List<LanguageDTO> lingue;
	private String descrizione;
	private String imgProfileHashCode;
	private SubscriptionDTO subscription;
	
	private String lastPositionLat;
	private String lastPositionLong;
	private String lastPositionDate;
	
	public UserDTO() {
	}
	

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
	@XmlElement public String getSesso() {
		return sesso;
	}
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	@XmlElement public NationDTO getNazionalita() {
		return nazionalita;
	}
	public void setNazionalita(NationDTO nazionalita) {
		this.nazionalita = nazionalita;
	}
	@XmlElement public List<LanguageDTO> getLingue() {
		return lingue;
	}
	public void setLingue(List<LanguageDTO> lingue) {
		this.lingue = lingue;
	}
	@XmlElement public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	@XmlElement public String getImgProfileHashCode() {
		return imgProfileHashCode;
	}
	public void setImgProfileHashCode(String imgProfileHashCode) {
		this.imgProfileHashCode = imgProfileHashCode;
	}
	@XmlElement public SubscriptionDTO getSubscription() {
		return subscription;
	}
	public void setSubscription(SubscriptionDTO subscription) {
		this.subscription = subscription;
	}
	@XmlElement public String getLastPositionLat() {
		return lastPositionLat;
	}
	public void setLastPositionLat(String lastPositionLat) {
		this.lastPositionLat = lastPositionLat;
	}
	@XmlElement public String getLastPositionLong() {
		return lastPositionLong;
	}
	public void setLastPositionLong(String lastPositionLong) {
		this.lastPositionLong = lastPositionLong;
	}
	@XmlElement public String getLastPositionDate() {
		return lastPositionDate;
	}
	public void setLastPositionDate(String lastPositionDate) {
		this.lastPositionDate = lastPositionDate;
	}

	
}
