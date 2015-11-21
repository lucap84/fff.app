package it.fff.business.common.eo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "luogo")
public class PlaceEO implements EntityObject {

	private String nome;
	private double gpsLat;
	private double gpsLong;
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
	
}
