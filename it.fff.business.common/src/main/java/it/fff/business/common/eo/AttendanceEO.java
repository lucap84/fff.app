package it.fff.business.common.eo;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import it.fff.business.common.bo.UserBO;


@Entity
@Table(name = "partecipazione")
public class AttendanceEO implements EntityObject {

	int id;
	boolean isOrganizer;
	int numPartecipanti;
	boolean isValid;
	int statusId;
	
	@ManyToOne
	@JoinColumn(name = "Evento_ID", nullable = false)
	EventEO event;
	
	
	UserEO user;
	
	public boolean isOrganizer() {
		return isOrganizer;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	public UserEO getUser() {
		return user;
	}

	public void setUser(UserEO user) {
		this.user = user;
	}


	public EventEO getEvent() {
		return event;
	}


	public void setEvent(EventEO event) {
		this.event = event;
	}
	
	
}
