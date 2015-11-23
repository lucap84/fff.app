package it.fff.business.common.eo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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

	@Column(name = "Data_Posizione")
	private String lastPositionDate;		
	
	@Column(name = "Count_Aggiornamento")
	private int numUpdate;	
	
	@Column(name = "Flg_Attivo")
	private boolean flagAttivo;
	
	@ManyToOne
	@JoinColumn(name = "Nazionalita_ID")
	private NazioneEO nazionalita; 
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
		@JoinTable(name = "lingue_parlate", 
			joinColumns = 		 { @JoinColumn(name = "Utente_ID") }, 
			inverseJoinColumns = { @JoinColumn(name = "Lingua_ID") })
	private List<LinguaEO> lingue;
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "achievements_ottenuti", 
		joinColumns = 		 { @JoinColumn(name = "Utente_ID") }, 
		inverseJoinColumns = { @JoinColumn(name = "Achievement_ID") })	
	private List<AchievementEO> achievements;	

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	private AccountEO account;
	
	
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
	
	
	public int getNumUpdate() {
		return numUpdate;
	}

	public void setNumUpdate(int numUpdate) {
		this.numUpdate = numUpdate;
	}
	
	
	public String getLastPositionDate() {
		return lastPositionDate;
	}

	public void setLastPositionDate(String lastPositionDate) {
		this.lastPositionDate = lastPositionDate;
	}


	public boolean isFlagAttivo() {
		return flagAttivo;
	}

	public void setFlagAttivo(boolean flagAttivo) {
		this.flagAttivo = flagAttivo;
	}

	public NazioneEO getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(NazioneEO nazionalita) {
		this.nazionalita = nazionalita;
	}

	public List<LinguaEO> getLingue() {
		return lingue;
	}

	public void setLingue(List<LinguaEO> lingue) {
		this.lingue = lingue;
	}
	

	public List<AchievementEO> getAchievements() {
		return achievements;
	}

	public void setAchievements(List<AchievementEO> achievements) {
		this.achievements = achievements;
	}
	

	public AccountEO getAccount() {
		return account;
	}

	public void setAccount(AccountEO account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Utente: " + this.id + ", " + this.nome + ", " + this.cognome;
	}


}
