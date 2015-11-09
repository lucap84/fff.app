package it.fff.clientserver.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WriteResultDTO extends ResultDTO {
	
	private static final long serialVersionUID = -2437136485588097533L;
	private int affectedRecords;
	private String identifier;
	
	public WriteResultDTO() {
		this.identifier = "-1";
		this.affectedRecords = -1;
	}

	@XmlElement public int getAffectedRecords() {
		return affectedRecords;
	}

	public void setAffectedRecords(int affectedRecords) {
		this.affectedRecords = affectedRecords;
	}

	@XmlElement public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public String toString() {
		
		return super.toString()+" --> "+this.getClass().getName()+"(affectedRecords: "+affectedRecords+"; identifier: "+identifier+")";
	}

}
