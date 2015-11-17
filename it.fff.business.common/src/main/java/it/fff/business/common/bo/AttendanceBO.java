package it.fff.business.common.bo;

public class AttendanceBO implements BusinessObject{
	
	int id;
	UserBO user;
	EventBO event;
	boolean isOrganizer;
	int numPartecipanti;
	FeedbackBO feedback;
	boolean isValid;
	int statusId;
	
	
	public UserBO getUser() {
		return user;
	}
	public void setUser(UserBO user) {
		this.user = user;
	}
	public EventBO getEvent() {
		return event;
	}
	public void setEvent(EventBO event) {
		this.event = event;
	}
	public FeedbackBO getFeedback() {
		return feedback;
	}
	public void setFeedback(FeedbackBO feedback) {
		this.feedback = feedback;
	}
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
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	
}