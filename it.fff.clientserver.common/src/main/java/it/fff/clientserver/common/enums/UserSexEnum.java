package it.fff.clientserver.common.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;

@XmlEnum
public enum UserSexEnum {
	
	M("Male"),
	F("Female"),
	T("Transgender"),
	UNKNOWN("Unknown");
	
	private final String name;
	
	private UserSexEnum(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}
	

}
