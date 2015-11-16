package it.fff.business.common.bo;

import java.util.List;

public class UserBO implements BusinessObject {

	private int	id;
	private String nome;
	private String cognome;
	private String dataNascita;
	private String sesso;
	private String descrizione;
	private double lastPositionLat;
	private double lastPositionLong;
	private String lastPositionDate;		
	private int numUpdate;	
	private boolean flagAttivo;
	private NazioneBO nazionalita; 
	private List<LinguaBO> lingue;
	private List<AchievementBO> achievements;
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
	public NazioneBO getNazionalita() {
		return nazionalita;
	}
	public void setNazionalita(NazioneBO nazionalita) {
		this.nazionalita = nazionalita;
	}
	public List<LinguaBO> getLingue() {
		return lingue;
	}
	public void setLingue(List<LinguaBO> lingue) {
		this.lingue = lingue;
	}
	public List<AchievementBO> getAchievements() {
		return achievements;
	}
	public void setAchievements(List<AchievementBO> achievements) {
		this.achievements = achievements;
	}
	
	
}
