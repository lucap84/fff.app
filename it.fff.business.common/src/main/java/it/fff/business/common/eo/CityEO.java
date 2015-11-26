package it.fff.business.common.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "citta")
public class CityEO extends EntityObject {
	
	@Id
	@Column(name="ID")
	private Integer id;
	
	@Column(name="nome")
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "Nazione_ID", nullable = false)
	private NazioneEO nazione;

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

	public NazioneEO getNazione() {
		return nazione;
	}

	public void setNazione(NazioneEO nazione) {
		this.nazione = nazione;
	}
	

}
