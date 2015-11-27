package it.fff.business.common.bo;

public class CityBO implements BusinessObject {

	private int id;
	private String nome;
	private NazioneBO nazione;
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
	public NazioneBO getNazione() {
		return nazione;
	}
	public void setNazione(NazioneBO nazione) {
		this.nazione = nazione;
	}
	
	
}
