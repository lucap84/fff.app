package it.fff.business.common.bo;

import it.fff.clientserver.common.dto.EventDTO;
import it.fff.clientserver.common.dto.FeedbackDTO;
import it.fff.clientserver.common.dto.UserDTO;

public class AttendanceBO implements BusinessObject{
	
	String id;
	UserDTO user;
	EventDTO event;
	boolean isOrganizer;
	int numPartecipanti;
	FeedbackDTO feedback;
	boolean isValid;
	int statusId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public EventDTO getEvent() {
		return event;
	}
	public void setEvent(EventDTO event) {
		this.event = event;
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
	public FeedbackDTO getFeedback() {
		return feedback;
	}
	public void setFeedback(FeedbackDTO feedback) {
		this.feedback = feedback;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	
}
