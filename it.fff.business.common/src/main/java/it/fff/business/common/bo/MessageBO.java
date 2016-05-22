package it.fff.business.common.bo;

public class MessageBO {

	private int	id;
	private String text;
	private String dataCreazione;
	private int eventId;
	private int attendanceId;
	private MessageStandardBO msgStd;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDataCreazione() {
		return dataCreazione;
	}
	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	public MessageStandardBO getMsgStd() {
		return msgStd;
	}
	public void setMsgStd(MessageStandardBO msgStd) {
		this.msgStd = msgStd;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public int getAttendanceId() {
		return attendanceId;
	}
	public void setAttendanceId(int attendanceId) {
		this.attendanceId = attendanceId;
	}
	
}
