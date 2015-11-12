package it.fff.business.common.bo;

public class SubscriptionBO implements BusinessObject{

	private int id;
	private int durata;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDurata() {
		return durata;
	}
	public void setDurata(int durata) {
		this.durata = durata;
	}
	
	
}
