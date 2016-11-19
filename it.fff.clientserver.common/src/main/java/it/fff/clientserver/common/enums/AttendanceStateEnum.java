package it.fff.clientserver.common.enums;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;

@XmlEnum
public enum AttendanceStateEnum {
	
	UNKNOW,
	UNDETECTED,
	OUTPLACE,
	INPLACE;
	
	private int id;

	@XmlElement public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
