package it.fff.clientserver.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import it.fff.clientserver.common.enums.AttendanceStateEnum;

@XmlRootElement
public class AttendanceDTO extends DataTransferObject {

	private static final long serialVersionUID = 4164922390980044360L;
	private String id;
	private UserDTO user;
	private String eventId;
	private boolean isOrganizer;
	private String numPartecipanti;
	private boolean isPositiveFeedback;
	private boolean isValid;
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
	@XmlElement public boolean isPositiveFeedback() {
		return isPositiveFeedback;
	}
	public void setPositiveFeedback(boolean isPositiveFeedback) {
		this.isPositiveFeedback = isPositiveFeedback;
	}
	@XmlElement public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	
}
