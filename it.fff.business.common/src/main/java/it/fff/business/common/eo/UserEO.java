package it.fff.business.common.eo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "utente")
public class UserEO implements EntityObject {
	
	public UserEO(){
		
	}

	public UserEO(String nome, String cognome){
		this.nome = nome;
		this.cognome = cognome;
	}	
	
	public UserEO(int id, String nome, String cognome){
		this(nome, cognome);
		this.id = id;
	}
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer	id;
	
	@Column(name = "Nome")
	private String nome;
	
	@Column(name = "Cognome")
	private String cognome;
	
	@Column(name = "Data_Nascita")
	private String dataNascita;
	
	@Column(name = "Sesso")
	private String sesso;
	
	@Column(name = "Descrizione")
	private String descrizione;

	@Column(name = "Gps_Latitudine")
	private double lastPositionLat;
	
	@Column(name = "Gps_Longitudine")
	private double lastPositionLong;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getLastPositionLat() {
		return lastPositionLat;
	}

	public void setLastPositionLat(double lastPositionLat) {
		this.lastPositionLat = lastPositionLat;
	}

	public double getLastPositionLong() {
		return lastPositionLong;
	}

	public void setLastPositionLong(double lastPositionLong) {
		this.lastPositionLong = lastPositionLong;
	}
	
	@Override
	public String toString() {
		return "Utente: " + this.id + ", " + this.nome + ", " + this.cognome;
	}


}
