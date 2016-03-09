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
	
	@Column(name = "ISO_31661_aplha2")
	private String internationalCodeAplha2;
	
	@Column(name = "ISO_31661_aplha3")
	private String internationalCodeAplha3;

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
	

	public String getInternationalCodeAplha2() {
		return internationalCodeAplha2;
	}

	public void setInternationalCodeAplha2(String internationalCodeAplha2) {
		this.internationalCodeAplha2 = internationalCodeAplha2;
	}

	public String getInternationalCodeAplha3() {
		return internationalCodeAplha3;
	}

	public void setInternationalCodeAplha3(String internationalCodeAplha3) {
		this.internationalCodeAplha3 = internationalCodeAplha3;
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
	public void setInternationalCodeAlpha2IfNotEmpty(String internationalCodeAplha2) {
		if(!isEmpty(internationalCodeAplha2)) this.internationalCodeAplha2 = internationalCodeAplha2;
	}
	public void setInternationalCodeAlpha3IfNotEmpty(String internationalCodeAplha3) {
		if(!isEmpty(internationalCodeAplha3)) this.internationalCodeAplha3 = internationalCodeAplha3;
	}	
	
	
	
}
