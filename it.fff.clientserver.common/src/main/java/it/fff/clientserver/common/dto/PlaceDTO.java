package it.fff.clientserver.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import it.fff.clientserver.common.enums.PlaceTypeEnum;

@XmlRootElement
public class PlaceDTO extends DataTransferObject {
	
	private static final long serialVersionUID = -6876900976330417334L;
	
	private int id;
	private String placeKey;
	private String nome;
	private double gpsLat;
	private double gpsLong;
	private String route;
	private String civico;
	private String cap;
	private CityDTO city;
	private PlaceTypeEnum placeType;
	
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
	@XmlElement public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	@XmlElement public double getGpsLat() {
		return gpsLat;
	}
	public void setGpsLat(double gpsLat) {
		this.gpsLat = gpsLat;
	}
	@XmlElement public double getGpsLong() {
		return gpsLong;
	}
	public void setGpsLong(double gpsLong) {
		this.gpsLong = gpsLong;
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
	@XmlElement public CityDTO getCity() {
		return city;
	}
	public void setCity(CityDTO city) {
		this.city = city;
	}
	@XmlElement public PlaceTypeEnum getPlaceType() {
		return placeType;
	}
	public void setPlaceType(PlaceTypeEnum placeType) {
		this.placeType = placeType;
	}
	@XmlElement public String getPlaceKey() {
		return placeKey;
	}
	public void setPlaceKey(String placeKey) {
		this.placeKey = placeKey;
	}
	
	
}
