package it.fff.business.common.bo;

public class AttendanceBO implements BusinessObject{
	
	private int id;
	private boolean isOrganizer;
	private int numPartecipanti;
	private boolean isPositiveFeedback;
	private boolean isValid;
	private EventBO event;
	private UserBO utente;
	private AttendanceStateBO stato;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isOrganizer() {
		return isOrganizer;
	}
	public void setOrganizer(boolean isOrganizer) {
		this.isOrganizer = isOrganizer;
	}
	public int getNumPartecipanti() {
		return numPartecipanti;
	}
	public void setNumPartecipanti(int numPartecipanti) {
		this.numPartecipanti = numPartecipanti;
	}
	public boolean isPositiveFeedback() {
		return isPositiveFeedback;
	}
	public void setPositiveFeedback(boolean isPositiveFeedback) {
		this.isPositiveFeedback = isPositiveFeedback;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public EventBO getEvent() {
		return event;
	}
	public void setEvent(EventBO event) {
		this.event = event;
	}
	public UserBO getUtente() {
		return utente;
	}
	public void setUtente(UserBO utente) {
		this.utente = utente;
	}
	public AttendanceStateBO getStato() {
		return stato;
	}
	public void setStato(AttendanceStateBO stato) {
		this.stato = stato;
	}
	
	
}
