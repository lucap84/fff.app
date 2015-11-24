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
public class SubscriptionTypeEO implements EntityObject {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer	id;
	
	@Column(name = "Nome")
	private String nome;

	@Column(name = "Descrizione")
	private String descrizione;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoAbbonamento", cascade = CascadeType.ALL)
	private List<PriceEO> prezzi;
	
}
