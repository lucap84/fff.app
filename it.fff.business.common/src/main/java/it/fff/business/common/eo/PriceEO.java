package it.fff.business.common.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prezzo_per_nazione")
public class PriceEO extends EntityObject {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer	id;
	
	@ManyToOne
	@JoinColumn(name = "Tipo_Abbonamento_ID", nullable = false)
	private SubscriptionTypeEO tipoAbbonamento;
	
	@ManyToOne
	@JoinColumn(name = "Nazione_ID", nullable = false)
	private NationEO nazione;
	
	@Column(name = "Prezzo")
	private Double prezzo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SubscriptionTypeEO getTipoAbbonamento() {
		return tipoAbbonamento;
	}

	public void setTipoAbbonamento(SubscriptionTypeEO tipoAbbonamento) {
		this.tipoAbbonamento = tipoAbbonamento;
	}

	public NationEO getNazione() {
		return nazione;
	}

	public void setNazione(NationEO nazione) {
		this.nazione = nazione;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	
}
