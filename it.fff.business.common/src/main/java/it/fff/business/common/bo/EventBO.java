package it.fff.business.common.bo;

import java.util.List;

public class EventBO implements BusinessObject{

	private int id;
	private String titolo;
	private String descrizione;
	private int durata;
	private String dataInizio;
	private EventStateBO stato;
	private EventCategoryBO categoria;
	private PlaceBO location;	
	private List<AttendanceBO> partecipazioni;
	private List<MessageBO> messages;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public int getDurata() {
		return durata;
	}
	public void setDurata(int durata) {
		this.durata = durata;
	}
	public String getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}
	public EventStateBO getStato() {
		return stato;
	}
	public void setStato(EventStateBO stato) {
		this.stato = stato;
	}
	public EventCategoryBO getCategoria() {
		return categoria;
	}
	public void setCategoria(EventCategoryBO categoria) {
		this.categoria = categoria;
	}
	public PlaceBO getLocation() {
		return location;
	}
	public void setLocation(PlaceBO location) {
		this.location = location;
	}
	public List<AttendanceBO> getPartecipazioni() {
		return partecipazioni;
	}
	public void setPartecipazioni(List<AttendanceBO> partecipazioni) {
		this.partecipazioni = partecipazioni;
	}
	public List<MessageBO> getMessages() {
		return messages;
	}
	public void setMessages(List<MessageBO> messages) {
		this.messages = messages;
	}
	
	
}
