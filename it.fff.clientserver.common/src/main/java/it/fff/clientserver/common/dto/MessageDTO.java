package it.fff.clientserver.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MessageDTO extends DataTransferObject {
	
	private static final long serialVersionUID = -2780061228505810088L;
	
	private String	id;
	private String text;
	private String dataCreazione;
	private EventDTO event;
	private AttendanceDTO attendance;
	boolean isStandard;
	
	@XmlElement public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@XmlElement public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@XmlElement public String getDataCreazione() {
		return dataCreazione;
	}
	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	@XmlElement public EventDTO getEvent() {
		return event;
	}
	public void setEvent(EventDTO event) {
		this.event = event;
	}
	@XmlElement public AttendanceDTO getAttendance() {
		return attendance;
	}
	public void setAttendance(AttendanceDTO attendance) {
		this.attendance = attendance;
	}
	@XmlElement public boolean isStandard() {
		return isStandard;
	}
	public void setStandard(boolean isStandard) {
		this.isStandard = isStandard;
	}
	
}
