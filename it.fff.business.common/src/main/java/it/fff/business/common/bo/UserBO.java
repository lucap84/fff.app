package it.fff.business.common.bo;

import java.util.List;

import it.fff.clientserver.common.enums.FeedbackEnum;
import it.fff.clientserver.common.enums.UserSexEnum;

public class UserBO implements BusinessObject {

	private int	id;
	private String nome;
	private String cognome;
	private String dataNascita;
	private UserSexEnum sesso;
	private String descrizione;
	private double lastPositionLat;
	private double lastPositionLong;
	private String lastPositionDate;		
	private int numUpdate;	
	private boolean flagAttivo;
	private NationBO nazionalita; 
	private List<LanguageBO> lingue;
	private List<AchievementBO> achievements;
	private AccountBO account;
	private List<FeedbackEnum> feedbacks;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public UserSexEnum getSesso() {
		return sesso;
	}
	public void setSesso(UserSexEnum sesso) {
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
	public String getLastPositionDate() {
		return lastPositionDate;
	}
	public void setLastPositionDate(String lastPositionDate) {
		this.lastPositionDate = lastPositionDate;
	}
	public int getNumUpdate() {
		return numUpdate;
	}
	public void setNumUpdate(int numUpdate) {
		this.numUpdate = numUpdate;
	}
	public boolean isFlagAttivo() {
		return flagAttivo;
	}
	public void setFlagAttivo(boolean flagAttivo) {
		this.flagAttivo = flagAttivo;
	}
	public NationBO getNazionalita() {
		return nazionalita;
	}
	public void setNazionalita(NationBO nazionalita) {
		this.nazionalita = nazionalita;
	}
	public List<LanguageBO> getLingue() {
		return lingue;
	}
	public void setLingue(List<LanguageBO> lingue) {
		this.lingue = lingue;
	}
	public List<AchievementBO> getAchievements() {
		return achievements;
	}
	public void setAchievements(List<AchievementBO> achievements) {
		this.achievements = achievements;
	}
	public AccountBO getAccount() {
		return account;
	}
	public void setAccount(AccountBO account) {
		this.account = account;
	}
	public List<FeedbackEnum> getFeedbacks() {
		return feedbacks;
	}
	public void setFeedbacks(List<FeedbackEnum> feedbacks) {
		this.feedbacks = feedbacks;
	}
	
}
