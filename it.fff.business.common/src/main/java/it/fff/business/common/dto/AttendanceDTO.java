package it.fff.business.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AttendanceDTO extends DataTransferObject {

	private static final long serialVersionUID = 4164922390980044360L;
	String id;
	UserDTO user;
	EventDTO event;
	boolean isOrganizer;
	int numPartecipanti;
	FeedbackDTO feedback;
	boolean isValid;
	int statusId;
	

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
	@XmlElement public int getNumPartecipanti() {
		return numPartecipanti;
	}
	public void setNumPartecipanti(int numPartecipanti) {
		this.numPartecipanti = numPartecipanti;
	}
	@XmlElement public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	@XmlElement public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	@XmlElement public FeedbackDTO getFeedback() {
		return feedback;
	}
	public void setFeedback(FeedbackDTO feedback) {
		this.feedback = feedback;
	}
	
}
