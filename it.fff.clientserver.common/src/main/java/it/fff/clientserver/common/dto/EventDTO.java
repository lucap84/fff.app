package it.fff.clientserver.common.dto;


import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EventDTO  extends DataTransferObject{

	private static final long serialVersionUID = -6847395962948284696L;
	private String id;
	private String titolo;
	private String descrizione;
	private String durata;
	private String dataInizio;
	private EventStateDTO stato;
	private EventCategoryDTO categoria;
	private PlaceDTO location;	
	private List<AttendanceDTO> partecipazioni;
	private List<MessageDTO> messages;
	
	@XmlElement public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@XmlElement public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	@XmlElement public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	@XmlElement public String getDurata() {
		return durata;
	}
	public void setDurata(String durata) {
		this.durata = durata;
	}
	@XmlElement public String getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}
	@XmlElement public EventStateDTO getStato() {
		return stato;
	}
	public void setStato(EventStateDTO stato) {
		this.stato = stato;
	}
	@XmlElement public EventCategoryDTO getCategoria() {
		return categoria;
	}
	public void setCategoria(EventCategoryDTO categoria) {
		this.categoria = categoria;
	}
	@XmlElement public PlaceDTO getLocation() {
		return location;
	}
	public void setLocation(PlaceDTO location) {
		this.location = location;
	}
	@XmlElement public List<AttendanceDTO> getPartecipazioni() {
		return partecipazioni;
	}
	public void setPartecipazioni(List<AttendanceDTO> partecipazioni) {
		this.partecipazioni = partecipazioni;
	}
	@XmlElement public List<MessageDTO> getMessages() {
		return messages;
	}
	public void setMessages(List<MessageDTO> messages) {
		this.messages = messages;
	}
	
	
	
	
}
