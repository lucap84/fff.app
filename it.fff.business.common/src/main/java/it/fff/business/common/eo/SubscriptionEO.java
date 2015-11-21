package it.fff.business.common.eo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "abbonamento")
public class SubscriptionEO implements EntityObject {

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
