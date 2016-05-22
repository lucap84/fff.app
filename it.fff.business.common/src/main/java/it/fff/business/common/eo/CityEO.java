package it.fff.business.common.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "citta")
public class CityEO extends EntityObject {
	
	@Id
	@Column(name="ID")
	@GeneratedValue
	private Integer id;
	
	@Column(name="nome")
	private String nome;
	
	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "Nazione_ID", nullable = false)
	private NationEO nazione;

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

	public NationEO getNazione() {
		return nazione;
	}

	public void setNazione(NationEO nazione) {
		this.nazione = nazione;
	}

	/*
	 * if not empty setter
	 */
	public void setIdIfNotEmpty(Integer id) {
		if(!isEmpty(id)) this.setId(id);
	}
	public void setNomeIfNotEmpty(String nome) {
		if(!isEmpty(nome)) this.setNome(nome);
	}
	

}
