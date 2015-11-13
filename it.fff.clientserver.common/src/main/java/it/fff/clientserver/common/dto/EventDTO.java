package it.fff.clientserver.common.dto;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EventDTO  extends DataTransferObject{

	private static final long serialVersionUID = -6847395962948284696L;
	private String id;
	private String nome;
	private String descrizione;
	private UserDTO userOrganizer;
	
	public EventDTO(){
		super();
	}

	public EventDTO(String eventId, String nome, String descrizione){
		this.id = eventId;
		this.nome = nome;
		this.descrizione = descrizione;
	}
	
	@XmlElement	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
