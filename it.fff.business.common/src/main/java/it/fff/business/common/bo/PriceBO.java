package it.fff.business.common.bo;

public class PriceBO implements BusinessObject {

	private int	id;
	private SubscriptionTypeBO tipoAbbonamento;
	private NationBO nazione;
	private double prezzo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public SubscriptionTypeBO getTipoAbbonamento() {
		return tipoAbbonamento;
	}
	public void setTipoAbbonamento(SubscriptionTypeBO tipoAbbonamento) {
		this.tipoAbbonamento = tipoAbbonamento;
	}
	public NationBO getNazione() {
		return nazione;
	}
	public void setNazione(NationBO nazione) {
		this.nazione = nazione;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	
}
