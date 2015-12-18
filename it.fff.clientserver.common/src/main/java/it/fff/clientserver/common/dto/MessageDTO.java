package it.fff.clientserver.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MessageDTO extends DataTransferObject {
	
	private static final long serialVersionUID = -2780061228505810088L;
	
	private int	id;
	private String text;
	private String dataCreazione;
	private int eventId;
	private int attendanceId;
	private boolean isStandard;
	
	@XmlElement public int getId() {
		return id;
	}
	public void setId(int id) {
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
	@XmlElement public boolean isStandard() {
		return isStandard;
	}
	public void setStandard(boolean isStandard) {
		this.isStandard = isStandard;
	}
	@XmlElement public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	@XmlElement public int getAttendanceId() {
		return attendanceId;
	}
	public void setAttendanceId(int attendanceId) {
		this.attendanceId = attendanceId;
	}
	
}
