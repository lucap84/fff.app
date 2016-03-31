package it.fff.clientserver.common.enums;

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
