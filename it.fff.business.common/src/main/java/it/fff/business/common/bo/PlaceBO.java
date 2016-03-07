package it.fff.business.common.bo;

import it.fff.clientserver.common.enums.PlaceTypeEnum;

public class PlaceBO implements BusinessObject {

	private int id;
	private String placeKey;
	private String nome;
	private String addressRoute;
	private String civico;
	private double gpsLat;
	private double gpsLong;
	private String cap;
	private String dataCreazione;
	private String dataAggiornamento;	
	private CityBO city;
	private PlaceTypeEnum placeType;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlaceKey() {
		return placeKey;
	}
	public void setPlaceKey(String placeKey) {
		this.placeKey = placeKey;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAddressRoute() {
		return addressRoute;
	}
	public void setAddressRoute(String addressRoute) {
		this.addressRoute = addressRoute;
	}
	public String getCivico() {
		return civico;
	}
	public void setCivico(String civico) {
		this.civico = civico;
	}
	public double getGpsLat() {
		return gpsLat;
	}
	public void setGpsLat(double gpsLat) {
		this.gpsLat = gpsLat;
	}
	public double getGpsLong() {
		return gpsLong;
	}
	public void setGpsLong(double gpsLong) {
		this.gpsLong = gpsLong;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getDataCreazione() {
		return dataCreazione;
	}
	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	public String getDataAggiornamento() {
		return dataAggiornamento;
	}
	public void setDataAggiornamento(String dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}
	public CityBO getCity() {
		return city;
	}
	public void setCity(CityBO city) {
		this.city = city;
	}
	public PlaceTypeEnum getPlaceType() {
		return placeType;
	}
	public void setPlaceType(PlaceTypeEnum placeType) {
		this.placeType = placeType;
	}

}
