package it.fff.clientserver.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import it.fff.clientserver.common.enums.AttendanceStateEnum;

@XmlRootElement
public class AttendanceDTO extends DataTransferObject {

	private static final long serialVersionUID = 4164922390980044360L;
	String id;
	UserDTO user;
	EventDTO event;
	boolean isOrganizer;
	String numPartecipanti;
	FeedbackDTO feedback;
	boolean isValid;
	private AttendanceStateEnum stato;
	

	@XmlElement public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@XmlElement public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	@XmlElement public EventDTO getEvent() {
		return event;
	}
	public void setEvent(EventDTO event) {
		this.event = event;
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
	@XmlElement public FeedbackDTO getFeedback() {
		return feedback;
	}
	public void setFeedback(FeedbackDTO feedback) {
		this.feedback = feedback;
	}
	@XmlElement public String getNumPartecipanti() {
		return numPartecipanti;
	}
	public void setNumPartecipanti(String numPartecipanti) {
		this.numPartecipanti = numPartecipanti;
	}
	public AttendanceStateEnum getStato() {
		return stato;
	}
	public void setStato(AttendanceStateEnum stato) {
		this.stato = stato;
	}
	
	
}
