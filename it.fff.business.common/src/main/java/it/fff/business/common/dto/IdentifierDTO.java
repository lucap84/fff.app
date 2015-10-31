package it.fff.business.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class IdentifierDTO  extends DataTransferObject{
	
	private static final long serialVersionUID = 6098032825366060640L;
	private int id;
	
	public IdentifierDTO() {
	}

	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	

}
