package it.fff.business.common.eo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "achievement")
public class AchievementTypeEO extends EntityObject {

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer	id;
	
	@Column(name = "Nome")
	private String nome;
	
	@Column(name = "Descrizione")
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
	 * setter if not empty
	 */

	public void setIdIfNotEmpty(Integer id) {
		if(!isEmpty(id)) this.setId(id);
	}
	public void setNomeIfNotEmpty(String nome) {
		if(!isEmpty(nome)) this.setNome(nome);
	}
	public void setDescrizioneIfNotEmpty(String descrizione) {
		if(!isEmpty(descrizione)) this.setDescrizione(descrizione);
	}	
	
	
}
