package it.fff.clientserver.common.enums;

public enum EventStateEnum {
	UNKNOW, 
	ACTIVE,
	ONGOING,
	FINISHED,
	CANCELED;
	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
