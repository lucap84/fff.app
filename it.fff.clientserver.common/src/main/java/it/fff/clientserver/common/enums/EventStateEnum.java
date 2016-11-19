package it.fff.clientserver.common.enums;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;

@XmlEnum
public enum EventStateEnum {
	UNKNOW, 
	ACTIVE,
	ONGOING,
	FINISHED,
	CANCELED;
	
	private int id;

	@XmlElement public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
