package it.fff.business.common.bo;

public class CityBO implements BusinessObject {

	private int id;
	private String nome;
	private NationBO nazione;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public NationBO getNazione() {
		return nazione;
	}
	public void setNazione(NationBO nazione) {
		this.nazione = nazione;
	}
	
	
}
