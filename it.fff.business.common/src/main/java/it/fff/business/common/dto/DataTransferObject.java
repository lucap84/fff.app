package it.fff.business.common.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

public class DataTransferObject implements Serializable{
	
	private static final long serialVersionUID = -7693503814004588654L;
	private boolean isErrorsPresent;
	private Map<String,String> errorsMap;
	
	public DataTransferObject(){
		this.errorsMap = new HashMap<String,String>();
		isErrorsPresent = false;
	}
	
	@XmlElement
	public boolean isErrorsPresent() {
		return isErrorsPresent;
	}
	public void setErrorsPresent(boolean isErrorsPresent) {
		this.isErrorsPresent = isErrorsPresent;
	}
	@XmlElement
	public Map<String, String> getErrorsMap() {
		return errorsMap;
	}
	public void setErrorsMap(Map<String, String> errorsMap) {
		this.errorsMap = errorsMap;
	}
	
	public void putErrorInMap(String code, String value){
		this.errorsMap.put(code, value);
	}
	
	

}
