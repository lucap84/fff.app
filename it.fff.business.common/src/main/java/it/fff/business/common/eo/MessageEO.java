package it.fff.business.common.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "messaggio")
public class MessageEO extends EntityObject {

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer	id;
	
	@Column(name = "Testo")
	private String text;
	
	@Column(name = "Data_creazione")
	private String dataCreazione;
	
	@ManyToOne
	@JoinColumn(name = "Evento_ID", nullable = false)
	private EventEO event;
	
	@ManyToOne
	@JoinColumn(name = "Partecipazione_ID", nullable = false)
	private AttendanceEO attendance;
	
	@ManyToOne
	@JoinColumn(name = "Messaggio_Standard_ID", nullable = false)
	private MessageStandardEO msgStd;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public EventEO getEvent() {
		return event;
	}

	public void setEvent(EventEO event) {
		this.event = event;
	}

	public AttendanceEO getAttendance() {
		return attendance;
	}

	public void setAttendance(AttendanceEO attendance) {
		this.attendance = attendance;
	}

	public MessageStandardEO getMsgStd() {
		return msgStd;
	}

	public void setMsgStd(MessageStandardEO msgStd) {
		this.msgStd = msgStd;
	}
	
}
