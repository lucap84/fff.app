package it.fff.clientserver.common.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import it.fff.clientserver.common.enums.UserSexEnum;

import javax.xml.bind.annotation.XmlElement;

@XmlRootElement
public class UserDTO extends DataTransferObject {
	
	private static final long serialVersionUID = -1979200792665108748L;
	private int	id;
	private String nome;
	private String cognome;
	private String dataNascita;
	private UserSexEnum sesso;
	private String descrizione;
	private String lastPositionLat;
	private String lastPositionLong;
	private String lastPositionDate;		
	private int numUpdate;	
	private boolean flagAttivo;
	private NationDTO nazionalita; 
	private List<LanguageDTO> lingue;
	private List<AchievementDTO> achievements;
	private AccountDTO account;
	private int numPositiveFeedbacks;
	private int numNegativeFeedbacks;
	
	@XmlElement public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	@XmlElement public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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
	@XmlElement public int getNumUpdate() {
		return numUpdate;
	}
	public void setNumUpdate(int numUpdate) {
		this.numUpdate = numUpdate;
	}
	@XmlElement public boolean isFlagAttivo() {
		return flagAttivo;
	}
	public void setFlagAttivo(boolean flagAttivo) {
		this.flagAttivo = flagAttivo;
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
	@XmlElement public List<AchievementDTO> getAchievements() {
		return achievements;
	}
	public void setAchievements(List<AchievementDTO> achievements) {
		this.achievements = achievements;
	}
	@XmlElement public AccountDTO getAccount() {
		return account;
	}
	public void setAccount(AccountDTO account) {
		this.account = account;
	}
	public int getNumPositiveFeedbacks() {
		return numPositiveFeedbacks;
	}
	public void setNumPositiveFeedbacks(int numPositiveFeedbacks) {
		this.numPositiveFeedbacks = numPositiveFeedbacks;
	}
	public int getNumNegativeFeedbacks() {
		return numNegativeFeedbacks;
	}
	public void setNumNegativeFeedbacks(int numNegativeFeedbacks) {
		this.numNegativeFeedbacks = numNegativeFeedbacks;
	}

}
