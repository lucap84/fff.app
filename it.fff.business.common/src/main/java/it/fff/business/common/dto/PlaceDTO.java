package it.fff.business.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlaceDTO extends DataTransferObject {
	
	private String nome;
	private String gpsLat;
	private String gpsLong;
	
	@XmlElement public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@XmlElement public String getGpsLat() {
		return gpsLat;
	}
	public void setGpsLat(String gpsLat) {
		this.gpsLat = gpsLat;
	}
	@XmlElement public String getGpsLong() {
		return gpsLong;
	}
	public void setGpsLong(String gpsLong) {
		this.gpsLong = gpsLong;
	}
	
	
	

}
