package it.fff.clientserver.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlaceDTO extends DataTransferObject {
	
	private static final long serialVersionUID = -6876900976330417334L;
	
	private int id;
	private String nome;
	private String gpsLat;
	private String gpsLong;
	private String via;
	private String civico;
	private String cap;
	private String tags;
	private CityDTO city;
	
	@XmlElement public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	@XmlElement public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	@XmlElement public String getCivico() {
		return civico;
	}
	public void setCivico(String civico) {
		this.civico = civico;
	}
	@XmlElement public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	@XmlElement public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	@XmlElement public CityDTO getCity() {
		return city;
	}
	public void setCity(CityDTO city) {
		this.city = city;
	}
	

}
