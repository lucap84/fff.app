package it.fff.business.common.bo;

public class MessageBO {

	private int	id;
	private String text;
	private String dataCreazione;
	private EventBO event;
	private AttendanceBO attendance;
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
	public EventBO getEvent() {
		return event;
	}
	public void setEvent(EventBO event) {
		this.event = event;
	}
	public AttendanceBO getAttendance() {
		return attendance;
	}
	public void setAttendance(AttendanceBO attendance) {
		this.attendance = attendance;
	}
	public MessageStandardBO getMsgStd() {
		return msgStd;
	}
	public void setMsgStd(MessageStandardBO msgStd) {
		this.msgStd = msgStd;
	}

	
	
}
