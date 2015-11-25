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
	private NazioneEO nazione;
	
	@Column(name = "Prezzo")
	private Double prezzo;

}
