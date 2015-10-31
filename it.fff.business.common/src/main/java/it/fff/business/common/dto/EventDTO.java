package it.fff.business.common.dto;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EventDTO extends DataTransferObject {

	private static final long serialVersionUID = -6847395962948284696L;
	private int eventId;
	private String nome;
	private String descrizione;
	
	public EventDTO(){
		super();
	}

	public EventDTO(int eventId, String nome, String descrizione){
		this.eventId = eventId;
		this.nome = nome;
		this.descrizione = descrizione;
	}
	
	@XmlElement
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	@XmlElement
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@XmlElement
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@Override
	public String toString() {
		return "EventDTO{" +
	            "eventId='" + eventId + '\'' +
	            ", nome=" + nome +
	             ", descrizione=" + descrizione +
	            '}';
	}
	
	
}
