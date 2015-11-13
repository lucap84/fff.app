package it.fff.business.common.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Evento")
public class EventEO implements EntityObject{

	@Id
	@Column(name="ID")
	private int id;
	
	private String nome;
	private String descrizione;
	
	public EventEO(){
		
	}

	public EventEO(int eventId, String nome, String descrizione){
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
	
	@Override
	public String toString() {
		return this.getClass().getName()+"("+id+")";
	}
	
	
}
