package it.fff.business.common.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lingua")
public class LanguageEO extends EntityObject {

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer	id;
	
	@Column(name = "Nome")
	private String nome;
	
	@Column(name = "ISO_639_1")
	private String iso639_1;

	@Column(name = "ISO_639_2")
	private String iso639_2;
	
	@Column(name = "ISO_639_3")
	private String iso639_3;
	
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

	public String getIso639_1() {
		return iso639_1;
	}

	public void setIso639_1(String iso639_1) {
		this.iso639_1 = iso639_1;
	}

	public String getIso639_2() {
		return iso639_2;
	}

	public void setIso639_2(String iso639_2) {
		this.iso639_2 = iso639_2;
	}

	public String getIso639_3() {
		return iso639_3;
	}

	public void setIso639_3(String iso639_3) {
		this.iso639_3 = iso639_3;
	}
	
	/*
	 * setter if not empty
	 */
	public void setIdIfNotEmpty(Integer id) {
		if(!isEmpty(id)) this.setId(id);
	}
	public void setNomeIfNotEmpty(String nome) {
		if(!isEmpty(nome)) this.setNome(nome);
	}
	public void setIso639_1IfNotEmpty(String iso639_1) {
		if(!isEmpty(iso639_1)) this.setIso639_1(iso639_1);
	}
	public void setIso639_2IfNotEmpty(String iso639_2) {
		if(!isEmpty(iso639_2)) this.setIso639_2(iso639_2);
	}
	public void setIso639_3IfNotEmpty(String iso639_3) {
		if(!isEmpty(iso639_3)) this.setIso639_3(iso639_3);
	}
	
}
