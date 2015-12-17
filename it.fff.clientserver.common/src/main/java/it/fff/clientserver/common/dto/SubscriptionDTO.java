package it.fff.clientserver.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class SubscriptionDTO extends DataTransferObject {

	private static final long serialVersionUID = 2676798868000641946L;
	
	private String	id;
	private String dataInizio;
	private String dataFine;
	private String sconto;
	private String userIdAbbonato;
	private SubscriptionTypeDTO tipo;
	
	@XmlElement public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@XmlElement public String getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}
	@XmlElement public String getDataFine() {
		return dataFine;
	}
	public void setDataFine(String dataFine) {
		this.dataFine = dataFine;
	}
	@XmlElement public String getSconto() {
		return sconto;
	}
	public void setSconto(String sconto) {
		this.sconto = sconto;
	}
	@XmlElement public String getUserIdAbbonato() {
		return userIdAbbonato;
	}
	public void setUserIdAbbonato(String userIdAbbonato) {
		this.userIdAbbonato = userIdAbbonato;
	}
	@XmlElement public SubscriptionTypeDTO getTipo() {
		return tipo;
	}
	public void setTipo(SubscriptionTypeDTO tipo) {
		this.tipo = tipo;
	}
	
}
