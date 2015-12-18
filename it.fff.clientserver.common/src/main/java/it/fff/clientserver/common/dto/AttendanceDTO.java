package it.fff.clientserver.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import it.fff.clientserver.common.enums.AttendanceStateEnum;
import it.fff.clientserver.common.enums.FeedbackEnum;

@XmlRootElement
public class AttendanceDTO extends DataTransferObject {

	private static final long serialVersionUID = 4164922390980044360L;
	private int id;
	private int userId;
	private int eventId;
	private boolean isOrganizer;
	private String numPartecipanti;
	private FeedbackEnum feedback;
	private boolean isValid;
	private AttendanceStateEnum stato;
	

	@XmlElement public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@XmlElement public boolean isOrganizer() {
		return isOrganizer;
	}
	public void setOrganizer(boolean isOrganizer) {
		this.isOrganizer = isOrganizer;
	}
	@XmlElement public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	@XmlElement public String getNumPartecipanti() {
		return numPartecipanti;
	}
	public void setNumPartecipanti(String numPartecipanti) {
		this.numPartecipanti = numPartecipanti;
	}
	@XmlElement public AttendanceStateEnum getStato() {
		return stato;
	}
	public void setStato(AttendanceStateEnum stato) {
		this.stato = stato;
	}
	@XmlElement public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	@XmlElement public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@XmlElement public FeedbackEnum getFeedback() {
		return feedback;
	}
	public void setFeedback(FeedbackEnum feedback) {
		this.feedback = feedback;
	}
	
	
}
