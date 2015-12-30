package it.fff.business.common.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "luogo")
public class PlaceEO extends EntityObject {

	@Id
	@Column(name="ID")
	@GeneratedValue
	private Integer id;
	
	@Column(name="Nome")
	private String nome;
	
	@Column(name="Gps_Latitudine")
	private double gpsLat;
	
	@Column(name="Gps_Longitudine")
	private double gpsLong;
	
	@Column(name="Via")
	private String via;
	
	@Column(name="Civico")
	private String civico;
	
	@Column(name="Cap")
	private String cap;
	
	@Column(name="tags")
	private String tags;
	
	@ManyToOne
	@JoinColumn(name = "Citta_ID", nullable = false)
	private CityEO city;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public CityEO getCity() {
		return city;
	}

	public void setCity(CityEO city) {
		this.city = city;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public void setIdIfNotEmpty(int id) {
		if(!isEmpty(id))this.id = id;
	}
	public void setNomeIfNotEmpty(String nome) {
		if(!isEmpty(nome))this.nome = nome;
	}
	public void setViaIfNotEmpty(String via) {
		if(!isEmpty(via))this.via = via;
	}
	public void setCivicoIfNotEmpty(String civico) {
		if(!isEmpty(civico))this.civico = civico;
	}
	public void setGpsLatIfNotEmpty(double gpsLat) {
		if(!isEmpty(gpsLat))this.gpsLat = gpsLat;
	}
	public void setGpsLongIfNotEmpty(double gpsLong) {
		if(!isEmpty(gpsLong))this.gpsLong = gpsLong;
	}
	public void setCapIfNotEmpty(String cap) {
		if(!isEmpty(cap))this.cap = cap;
	}
	public void setTagsIfNotEmpty(String tags) {
		if(!isEmpty(tags))this.tags = tags;
	}
	
}
