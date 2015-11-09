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
	private String nazionalita;
	private List<String> lingue;
	private String descrizione;
	private String imgProfileHashCode;
	
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
	@XmlElement public String getNazionalita() {
		return nazionalita;
	}
	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}
	@XmlElement public List<String> getLingue() {
		return lingue;
	}
	public void setLingue(List<String> lingue) {
		this.lingue = lingue;
	}
	public void addLingua(String lingua){
		this.lingue.add(lingua);
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
	
	
	

}
