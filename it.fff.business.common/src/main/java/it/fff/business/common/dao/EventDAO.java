package it.fff.business.common.dao;


public class EventDAO implements DataAccessObject{

	private int eventId;
	private String nome;
	private String descrizione;
	
	public EventDAO(){
		
	}

	public EventDAO(int eventId, String nome, String descrizione){
		this.eventId = eventId;
		this.nome = nome;
		this.descrizione = descrizione;
	}
	
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@Override
	public String toString() {
		return "EventDAO{" +
	            "eventId='" + eventId + '\'' +
	            ", nome=" + nome +
	             ", descrizione=" + descrizione +
	            '}';
	}
	
	
}
