package it.fff.business.common.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nazione")
public class NationEO extends EntityObject {

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer	id;
	
	@Column(name = "Nome")
	private String nome;
	
	@Column(name = "International_Key")
	private String internationalKey;

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

	public String getInternationalKey() {
		return internationalKey;
	}

	public void setInternationalKey(String internationalKey) {
		this.internationalKey = internationalKey;
	}
	
	/*
	 * setter if not empty
	 */
	public void setIdIfNotEmpty(Integer id) {
		if(!isEmpty(id)) this.id = id;
	}
	public void setNomeIfNotEmpty(String nome) {
		if(!isEmpty(nome)) this.nome = nome;
	}

	public void setInternationalKeyIfNotEmpty(String internationalKey) {
		if(!isEmpty(internationalKey)) this.internationalKey = internationalKey;
	}	
	
	
}
