package it.fff.clientserver.common.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReadResultDTO<T> extends ResultDTO{

	private static final long serialVersionUID = 5366192629464578932L;
	private T dto;
	private List<T> dtos;
	
	public ReadResultDTO() {
		this.dto = null;
		this.dtos = new ArrayList<T>();
	}

	@XmlElement public T getDto() {
		return dto;
	}

	public void setDto(T dto) {
		this.dto = dto;
	}

	@XmlElement public List<T> getDtos() {
		return dtos;
	}

	public void setDtos(List<T> dtos) {
		this.dtos = dtos;
	}
	
	

}
