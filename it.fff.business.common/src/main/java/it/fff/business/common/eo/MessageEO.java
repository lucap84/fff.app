package it.fff.business.common.eo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "messaggio")
public class MessageEO extends EntityObject {

	private int id;
	private String text;
	private boolean isStandard;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isStandard() {
		return isStandard;
	}
	public void setStandard(boolean isStandard) {
		this.isStandard = isStandard;
	}
	
	
}
