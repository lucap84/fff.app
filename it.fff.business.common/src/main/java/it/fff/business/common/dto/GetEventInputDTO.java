package it.fff.business.common.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GetEventInputDTO  implements DataTransferObject, Serializable{
	
	private static final long serialVersionUID = 6098032825366060640L;
	private int eventId;
	
	public GetEventInputDTO() {
	}

	@XmlElement
	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	

}
