package it.fff.clientserver.common.dto;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

public class ResultDTO extends DataTransferObject{

	private static final long serialVersionUID = -8669750506658703064L;
	private boolean isOk;
	private Map<String,String> errorsMap;
	
	public ResultDTO(){
		this.errorsMap = new HashMap<String,String>();
		isOk = true;
	}
	
	@XmlElement public boolean isOk() {
		return isOk;
	}
	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	@XmlElement public Map<String, String> getErrorsMap() {
		return errorsMap;
	}
	public void setErrorsMap(Map<String, String> errorsMap) {
		this.errorsMap = errorsMap;
	}
	
	public void putErrorInMap(String code, String value){
		this.errorsMap.put(code, value);
	}
	
	@Override
	public String toString() {
		return this.getClass().getName()+"(isErrorsPresent: "+isOk+"; errorMap: "+errorsMap.toString()+")";
	}
}
