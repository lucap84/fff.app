package it.fff.clientserver.common.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

public class DataTransferObject implements Serializable{

	private static final long serialVersionUID = -455904852359230166L;
	private Map<String,String> errorsMap;
	
	public DataTransferObject(){
		this.errorsMap = null; //if errors are not present we want no elements in output
	}
	
	public Boolean isOk() {
		return (errorsMap==null || errorsMap.size()==0);
	}

	@XmlElement(required=false) 
	public Map<String, String> getErrorsMap() {
		return errorsMap;
	}
	public void setErrorsMap(Map<String, String> errorsMap) {
		this.errorsMap = errorsMap;
	}
	
	public void putErrorInMap(String code, String value){
		if(errorsMap==null){
			errorsMap = new HashMap<String,String>();
		}		
		this.errorsMap.put(code, value);
	}
	
}
