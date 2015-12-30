package it.fff.clientserver.common.enums;

public enum AttendanceStateEnum {
	UNKNOW,
	UNDETECTED,
	OUTPLACE,
	INPLACE;
	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
