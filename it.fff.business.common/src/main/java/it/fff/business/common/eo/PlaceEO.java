package it.fff.business.common.eo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name = "luogo")
public class PlaceEO extends EntityObject {

	@Id
	@Column(name="ID")
	@GeneratedValue
	private Integer id;

	@Column(name="Place_Key")
	private String placeKey;
	
	@Column(name="Nome")
	private String nome;
	
	@Column(name="Address_Route")
	private String addressRoute;
	
	@Column(name="Civico")
	private String civico;

	@Column(name="Gps_Latitudine")
	private Double gpsLat;
	
	@Column(name="Gps_Longitudine")
	private Double gpsLong;
	
	@Column(name="Cap")
	private String cap;
	
	@Column(name="Data_Creazione")
	private String dataCreazione;

	@Column(name="Data_Aggiornamento")
	private String dataAggiornamento;
	
	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "Citta_ID", nullable = false)
	private CityEO city;

	@ManyToOne
	@JoinColumn(name = "TipoLuogo_ID", nullable = false)
	private PlaceTypeEO placeType;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "relatedPlaces")
	private Set<KeywordEO> keywords;
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Double getGpsLat() {
		return gpsLat;
	}
	public void setGpsLat(Double gpsLat) {
		this.gpsLat = gpsLat;
	}
	public Double getGpsLong() {
		return gpsLong;
	}
	public void setGpsLong(Double gpsLong) {
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
	public CityEO getCity() {
		return city;
	}
	public void setCity(CityEO city) {
		this.city = city;
	}
	public PlaceTypeEO getPlaceType() {
		return placeType;
	}
	public void setPlaceType(PlaceTypeEO placeType) {
		this.placeType = placeType;
	}
	
	public Set<KeywordEO> getKeywords() {
		if(keywords==null){
			keywords = new HashSet<KeywordEO>();
		}
		return keywords;
	}
	public void setKeywords(Set<KeywordEO> keywords) {
		this.keywords = keywords;
	}	
	
	/*
	 * setter if not empty
	 */
	
	public void setIdIfNotEmpty(Integer id) {
		if(!isEmpty(id))this.setId(id);
	}
	public void setNomeIfNotEmpty(String nome) {
		if(!isEmpty(nome))this.setNome(nome);
	}
	public void setAddressRouteIfNotEmpty(String addressRoute) {
		if(!isEmpty(addressRoute))this.setAddressRoute(addressRoute);
	}
	public void setCivicoIfNotEmpty(String civico) {
		if(!isEmpty(civico))this.setCivico(civico);
	}
	public void setGpsLatIfNotEmpty(Double gpsLat) {
		if(!isEmpty(gpsLat))this.setGpsLat(gpsLat);
	}
	public void setGpsLongIfNotEmpty(Double gpsLong) {
		if(!isEmpty(gpsLong))this.setGpsLong(gpsLong);
	}
	public void setCapIfNotEmpty(String cap) {
		if(!isEmpty(cap))this.setCap(cap);
	}

	
	@Override
	public boolean equals(Object obj) {
		PlaceEO p2 = (PlaceEO)obj;
		return this.id.equals(p2.getId());
	}
	
	@Override
	public int hashCode() {
		return this.placeKey.hashCode();
	}
}
