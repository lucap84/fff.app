package it.fff.business.common.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "messaggio_standard")
public class MessageStandardEO extends EntityObject {

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer	id;
	
	@Column(name = "Testo")
	private String standardText;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStandardText() {
		return standardText;
	}

	public void setStandardText(String standardText) {
		this.standardText = standardText;
	}
	
	/*
	 * if not empty methods
	 */

	public void setIdIfNotEmpty(Integer id) {
		if(!isEmpty(id))this.setId(id);
	}
	public void setStandardTextIfNotEmpty(String standardText) {
		if(!isEmpty(standardText))this.setStandardText(standardText);
	}	
	
}
