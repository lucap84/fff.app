package it.fff.business.common.bo;

import java.util.List;

public class UserBO implements BusinessObject {

	private int id;
	private String email;
	private String nome;
	private String cognome;
	private String dataNascita;
	private String sesso;
	private String nazionalita;
	private List<String> lingue;
	private String descrizione;
	
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}
	public String getSesso() {
		return sesso;
	}
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	public String getNazionalita() {
		return nazionalita;
	}
	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}
	public List<String> getLingue() {
		return lingue;
	}
	public void setLingue(List<String> lingue) {
		this.lingue = lingue;
	}
	public void addLingua(String lingua){
		this.lingue.add(lingua);
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	
}
