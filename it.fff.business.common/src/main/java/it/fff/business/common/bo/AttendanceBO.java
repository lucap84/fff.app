package it.fff.business.common.bo;

import it.fff.clientserver.common.enums.AttendanceStateEnum;
import it.fff.clientserver.common.enums.FeedbackEnum;

public class AttendanceBO implements BusinessObject{
	
	private int id;
	private boolean isOrganizer;
	private int numeroOspiti;
	private FeedbackEnum feedback;
	private boolean isValid;
	private String dataCreazione;
	private String dataAggiornamento;
	private int eventId;
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
	public int getNumeroOspiti() {
		return numeroOspiti;
	}
	public void setNumeroOspiti(int numeroOspiti) {
		this.numeroOspiti = numeroOspiti;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
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
		if(feedback==null){
			feedback = FeedbackEnum.UNKNOW;
		}
		this.feedback = feedback;
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
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	
}
