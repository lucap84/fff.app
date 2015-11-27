package it.fff.business.common.bo;


public class PlaceBO implements BusinessObject {

	private int id;
	private String nome;
	private double gpsLat;
	private double gpsLong;
	private String via;
	private String civico;
	private String cap;
	private String tags;
	private CityBO city;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public String getCivico() {
		return civico;
	}
	public void setCivico(String civico) {
		this.civico = civico;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public CityBO getCity() {
		return city;
	}
	public void setCity(CityBO city) {
		this.city = city;
	}
	

}
