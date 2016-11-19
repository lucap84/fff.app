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
	private Integer durata;

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
	
	@Column(name="Data_Creazione")
	private String dataCreazione;
	
	@Column(name="Data_Aggiornamento")
	private String dataAggiornamento;
	
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

	public Integer getDurata() {
		return durata;
	}

	public void setDurata(Integer durata) {
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
	
	public String getDataAggiornamento() {
		return dataAggiornamento;
	}
	
	public void setDataAggiornamento(String dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}
	
	public String getDataCreazione() {
		return dataCreazione;
	}
	
	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	
	
	/*
	 * IfNotEmpty
	 */


	public void setIdIfNotEmpty(Integer id) {
		if(!isEmpty(id)) this.setId(id);
	}
	public void setTitoloIfNotEmpty(String titolo) {
		if(!isEmpty(titolo)) this.setTitolo(titolo);
	}
	public void setDescrizioneIfNotEmpty(String descrizione) {
		if(!isEmpty(descrizione)) this.setDescrizione(descrizione);
	}
	public void setDurataIfNotEmpty(int durata) {
		if(!isEmpty(durata)) this.setDurata(durata);
	}
	public void setDataInizioIfNotEmpty(String dataInizio) {
		if(!isEmpty(dataInizio)) this.setDataInizio(dataInizio);
	}
	public void setDataAggiornamentoIfNotEmpty(String dataAggiornamento) {
		if(!isEmpty(dataAggiornamento)) this.setDataAggiornamento(dataAggiornamento);
	}
	public void setDataCreazioneIfNotEmpty(String dataCreazione) {
		if(!isEmpty(dataCreazione)) this.setDataCreazione(dataCreazione);
	}
}
