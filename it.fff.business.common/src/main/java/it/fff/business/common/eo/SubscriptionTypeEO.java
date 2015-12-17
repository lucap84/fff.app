package it.fff.business.common.eo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_abbonamento")
public class SubscriptionTypeEO extends EntityObject {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer	id;
	
	@Column(name = "Nome")
	private String nome;

	@Column(name = "Descrizione")
	private String descrizione;
	
	@Column(name = "Durata_Giorni")
	private Integer	durataGiorni;
	
	@Column(name = "Durata_Mesi")
	private Integer	durataMesi;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoAbbonamento", cascade = CascadeType.ALL)
	private List<PriceEO> prezzi;

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

	public List<PriceEO> getPrezzi() {
		return prezzi;
	}
	
	public Integer getDurataGiorni() {
		return durataGiorni;
	}

	public void setDurataGiorni(Integer durataGiorni) {
		this.durataGiorni = durataGiorni;
	}

	public Integer getDurataMesi() {
		return durataMesi;
	}

	public void setDurataMesi(Integer durataMesi) {
		this.durataMesi = durataMesi;
	}

	public void setPrezzi(List<PriceEO> prezzi) {
		this.prezzi = prezzi;
	}
	
}
