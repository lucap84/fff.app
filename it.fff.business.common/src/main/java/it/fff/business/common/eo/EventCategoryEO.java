package it.fff.business.common.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categoria_evento")
public class EventCategoryEO extends EntityObject {

	@Id
	@Column(name="ID")
	private Integer id;
	
	@Column(name="Nome")
	private String nome;
	
	@Column(name="Descrizione")
	private String descrizione;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	/*
	 * if empty methods
	 */
	public void setIdIfNotEmpty(int id) {
		if(!isEmpty(id))this.id = id;
	}
	public void setNomeIfNotEmpty(String nome) {
		if(!isEmpty(nome))this.nome = nome;
	}

	public void setDescrizioneIfNotEmpty(String descrizione) {
		if(!isEmpty(descrizione))this.descrizione = descrizione;
	}
	
}
