package it.fff.business.common.bo;

import it.fff.clientserver.common.enums.AttendanceStateEnum;
import it.fff.clientserver.common.enums.FeedbackEnum;

public class AttendanceBO implements BusinessObject{
	
	private int id;
	private boolean isOrganizer;
	private int numPartecipanti;
	private FeedbackEnum feedback;
	private boolean isValid;
	private EventBO event;
	private UserBO utente;
	private AttendanceStateEnum stato;
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
	public AttendanceStateEnum getStato() {
		return stato;
	}
	public void setStato(AttendanceStateEnum stato) {
		this.stato = stato;
	}
	public FeedbackEnum getFeedback() {
		return feedback;
	}
	public void setFeedback(FeedbackEnum feedback) {
		this.feedback = feedback;
	}
	
}
