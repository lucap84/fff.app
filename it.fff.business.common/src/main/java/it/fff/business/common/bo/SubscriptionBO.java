package it.fff.business.common.bo;

public class SubscriptionBO implements BusinessObject{

	private int	id;
	private String dataInizio;
	private String dataFine;
	private double sconto;
	private int userIdAbbonato;
	private SubscriptionTypeBO tipo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public double getSconto() {
		return sconto;
	}
	public void setSconto(double sconto) {
		this.sconto = sconto;
	}
	public int getUserIdAbbonato() {
		return userIdAbbonato;
	}
	public void setUserIdAbbonato(int userIdAbbonato) {
		this.userIdAbbonato = userIdAbbonato;
	}
	public SubscriptionTypeBO getTipo() {
		return tipo;
	}
	public void setTipo(SubscriptionTypeBO tipo) {
		this.tipo = tipo;
	}

}
