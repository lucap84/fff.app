package it.fff.business.common.eo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="evento")
public class EventEO extends EntityObject{

	@Id
	@Column(name="ID")
	@GeneratedValue
	private Integer id;
	
	@Column(name="Titolo")
	private String titolo;
	
	@Column(name="Descrizione")
	private String descrizione;
	
	@Column(name="Durata")
	private int durata;

	@Column(name="Data_Inizio")
	private String dataInizio;

	@ManyToOne	//Unidirectional
	@JoinColumn(name = "Stato_ID", nullable = false)
	private EventStateEO stato;
	
	@ManyToOne	//Unidirectional
	@JoinColumn(name = "Categoria_ID", nullable = false)
	private EventCategoryEO categoria;
	
	@ManyToOne	//Unidirectional
	@JoinColumn(name = "Luogo_ID", nullable = false)
	private PlaceEO location;	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
	private List<AttendanceEO> partecipazioni;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
	private List<MessageEO> messages;
	
	public EventEO(){
		
	}

	public EventEO(int eventId, String titolo, String descrizione){
		this.id = eventId;
		this.titolo = titolo;
		this.descrizione = descrizione;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	public EventCategoryEO getCategoria() {
		return categoria;
	}

	public void setCategoria(EventCategoryEO categoria) {
		this.categoria = categoria;
	}

	public List<AttendanceEO> getPartecipazioni() {
		return partecipazioni;
	}

	public void setPartecipazioni(List<AttendanceEO> partecipazioni) {
		this.partecipazioni = partecipazioni;
	}

	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public String getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}

	public EventStateEO getStato() {
		return stato;
	}

	public void setStato(EventStateEO stato) {
		this.stato = stato;
	}

	public PlaceEO getLocation() {
		return location;
	}

	public void setLocation(PlaceEO location) {
		this.location = location;
	}

	public List<MessageEO> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageEO> messages) {
		this.messages = messages;
	}
	
	/*
	 * IfNotEmpty
	 */
	


	public void setIdIfNotEmpty(int id) {
		if(!isEmpty(id)) this.id = id;
	}

	public void setTitoloIfNotEmpty(String titolo) {
		if(!isEmpty(titolo)) this.titolo = titolo;
	}

	public void setDescrizioneIfNotEmpty(String descrizione) {
		if(!isEmpty(descrizione)) this.descrizione = descrizione;
	}
	
	

	@Override
	public String toString() {
		return this.getClass().getName()+"("+id+")";
	}
}
