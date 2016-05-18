package it.fff.business.common.eo;

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
import javax.persistence.Table;


@Entity
@Table(name = "utente")
public class UserEO extends EntityObject {
	
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
	private Double lastPositionLat;
	
	@Column(name = "Gps_Longitudine")
	private Double lastPositionLong;

	@Column(name = "Data_Posizione")
	private String lastPositionDate;		
	
	@Column(name = "Count_Aggiornamento")
	private Integer numUpdate;	
	
	@Column(name = "Flg_Attivo")
	private Boolean flagAttivo;
	
	@Column(name = "Data_Creazione")
	private String dataCreazione;
	
	@Column(name = "Data_Aggiornamento")
	private String dataAggiornamento;
	
	@ManyToOne
	@JoinColumn(name = "Nazionalita_ID")
	private NationEO nazionalita; 
	
	@ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(name = "lingua_parlata", 
			joinColumns = 		 { @JoinColumn(name = "Utente_ID") }, 
			inverseJoinColumns = { @JoinColumn(name = "Lingua_ID") })
	private List<LanguageEO> lingue;
	
	
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //Unidirezionale
//	@JoinTable(name = "achievement_ottenuto", 
//		joinColumns = 		 { @JoinColumn(name = "Utente_ID") }, 
//		inverseJoinColumns = { @JoinColumn(name = "Achievement_ID") })	
//	private List<AchievementTypeEO> achievements;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private List<AchievementObtainedEO> achievements;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	private AccountEO account;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "abbonato", cascade = CascadeType.ALL)
	private List<SubscriptionEO> abbonamenti;	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private List<ProfileImageEO> profileImages;	
	

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

	public Double getLastPositionLat() {
		return lastPositionLat;
	}

	public void setLastPositionLat(Double lastPositionLat) {
		this.lastPositionLat = lastPositionLat;
	}

	public Double getLastPositionLong() {
		return lastPositionLong;
	}

	public void setLastPositionLong(Double lastPositionLong) {
		this.lastPositionLong = lastPositionLong;
	}

	public String getLastPositionDate() {
		return lastPositionDate;
	}

	public void setLastPositionDate(String lastPositionDate) {
		this.lastPositionDate = lastPositionDate;
	}

	public Integer getNumUpdate() {
		return numUpdate;
	}

	public void setNumUpdate(Integer numUpdate) {
		this.numUpdate = numUpdate;
	}

	public Boolean getFlagAttivo() {
		return flagAttivo;
	}

	public void setFlagAttivo(Boolean flagAttivo) {
		this.flagAttivo = flagAttivo;
	}

	public String getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public String getDataAggiornamento() {
		return dataAggiornamento;
	}

	public void setDataAggiornamento(String dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}

	public NationEO getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(NationEO nazionalita) {
		this.nazionalita = nazionalita;
	}

	public List<LanguageEO> getLingue() {
		return lingue;
	}

	public void setLingue(List<LanguageEO> lingue) {
		this.lingue = lingue;
	}

	public List<AchievementObtainedEO> getAchievements() {
		return achievements;
	}

	public void setAchievements(List<AchievementObtainedEO> achievements) {
		this.achievements = achievements;
	}

	public AccountEO getAccount() {
		return account;
	}

	public void setAccount(AccountEO account) {
		this.account = account;
	}

	public List<SubscriptionEO> getAbbonamenti() {
		return abbonamenti;
	}

	public void setAbbonamenti(List<SubscriptionEO> abbonamenti) {
		this.abbonamenti = abbonamenti;
	}

	public List<ProfileImageEO> getProfileImages() {
		return profileImages;
	}

	public void setProfileImages(List<ProfileImageEO> profileImages) {
		this.profileImages = profileImages;
	}

	/*
	 * setter if not empty
	 */
	public void setIdIfNotEmpty(Integer id) {
		if(!isEmpty(id)) this.id = id;
	}
	public void setCognomeIfNotEmpty(String cognome) {
		if(!isEmpty(cognome)) this.cognome = cognome;
	}
	public void setNomeIfNotEmpty(String nome) {
		if(!isEmpty(nome)) this.nome = nome;
	}
	public void setSessoIfNotEmpty(String sesso) {
		if(!isEmpty(sesso)) this.sesso = sesso;
	}
	public void setDataNascitaIfNotEmpty(String dataNascita) {
		if(!isEmpty(dataNascita)) this.dataNascita = dataNascita;
	}
	public void setDescrizioneIfNotEmpty(String descrizione) {
		if(!isEmpty(descrizione)) this.descrizione = descrizione;
	}
	public void setLastPositionLatIfNotEmpty(Double lastPositionLat) {
		if(!isEmpty(lastPositionLat)) this.lastPositionLat = lastPositionLat;
	}
	public void setLastPositionLongIfNotEmpty(Double lastPositionLong) {
		if(!isEmpty(lastPositionLong)) this.lastPositionLong = lastPositionLong;
	}
	public void setLastPositionDateIfNotEmpty(String lastPositionDate) {
		if(!isEmpty(lastPositionDate)) this.lastPositionDate = lastPositionDate;
	}

}
