package it.fff.business.common.dto;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EventDTO extends DataTransferObject {

	private static final long serialVersionUID = -6847395962948284696L;
	private String eventId;
	private String nome;
	private String descrizione;
	private UserDTO userOrganizer;
	
	public EventDTO(){
		super();
	}

	public EventDTO(String eventId, String nome, String descrizione){
		this.eventId = eventId;
		this.nome = nome;
		this.descrizione = descrizione;
	}
	
	@XmlElement	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	@XmlElement	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	@XmlElement public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@XmlElement public UserDTO getUserOrganizer() {
		return userOrganizer;
	}

	public void setUserOrganizer(UserDTO userOrganizer) {
		this.userOrganizer = userOrganizer;
	}
	
	
	
	
}
