package it.fff.clientserver.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MessageStandardDTO extends DataTransferObject{
	
	private int id;
	private String text;

	@XmlElement public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	

}
