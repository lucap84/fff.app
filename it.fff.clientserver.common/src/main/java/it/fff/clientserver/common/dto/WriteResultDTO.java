package it.fff.clientserver.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WriteResultDTO extends DataTransferObject {
	
	private static final long serialVersionUID = -2437136485588097533L;
	private int affectedRecords;
	private int identifier;
	
	public WriteResultDTO() {
		this.identifier = -1;
		this.affectedRecords = -1;
	}

	@XmlElement public int getAffectedRecords() {
		return affectedRecords;
	}

	public void setAffectedRecords(int affectedRecords) {
		this.affectedRecords = affectedRecords;
	}

	@XmlElement public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}
	

	@Override
	public String toString() {
		
		return super.toString()+" --> "+this.getClass().getName()+"(affectedRecords: "+affectedRecords+"; identifier: "+identifier+")";
	}

	
}
