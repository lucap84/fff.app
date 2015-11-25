package it.fff.business.common.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "abbonamento")
public class SubscriptionEO extends EntityObject {

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer	id;
	
	@ManyToOne
	@JoinColumn(name = "Utente_ID", nullable = false)
	private UserEO abbonato;
	
	@ManyToOne
	@JoinColumn(name = "Tipo_ID", nullable = false)
	private SubscriptionTypeEO tipo;
	
	@Column(name = "Data_Inizio")
	private String dataInizio;
	
	@Column(name = "Data_Fine")
	private String dataFine;
	
	@Column(name = "Sconto")
	private Double sconto;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserEO getAbbonato() {
		return abbonato;
	}

	public void setAbbonato(UserEO abbonato) {
		this.abbonato = abbonato;
	}

	public SubscriptionTypeEO getTipo() {
		return tipo;
	}

	public void setTipo(SubscriptionTypeEO tipo) {
		this.tipo = tipo;
	}

	public String getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getDataFine() {
		return dataFine;
	}

	public void setDataFine(String dataFine) {
		this.dataFine = dataFine;
	}

	public Double getSconto() {
		return sconto;
	}

	public void setSconto(Double sconto) {
		this.sconto = sconto;
	}
	
	
	
}
