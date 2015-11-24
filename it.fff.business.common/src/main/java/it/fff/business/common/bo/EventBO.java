package it.fff.business.common.bo;

import it.fff.clientserver.common.dto.UserDTO;

public class EventBO implements BusinessObject{

	private int id;
	private String nome;
	private String descrizione;
	private UserBO userOrganizer;
	
	public EventBO(){
		
	}

	public EventBO(int eventId, String nome, String descrizione){
		this.id = eventId;
		this.nome = nome;
		this.descrizione = descrizione;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public UserBO getUserOrganizer() {
		return userOrganizer;
	}

	public void setUserOrganizer(UserBO userOrganizer) {
		this.userOrganizer = userOrganizer;
	}

	@Override
	public String toString() {
		return "EventBO{" +
	            "eventId='" + id + '\'' +
	            ", nome=" + nome +
	             ", descrizione=" + descrizione +
	            '}';
	}
	
	
}
