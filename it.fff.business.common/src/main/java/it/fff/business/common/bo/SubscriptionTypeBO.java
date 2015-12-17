package it.fff.business.common.bo;

public class SubscriptionTypeBO {

	private int id;
	private String nome;
	private int durataGiorni;
	private int durataMesi;
	private String descrizione;

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
	
	public int getDurataGiorni() {
		return durataGiorni;
	}

	public void setDurataGiorni(int durataGiorni) {
		this.durataGiorni = durataGiorni;
	}

	public int getDurataMesi() {
		return durataMesi;
	}

	public void setDurataMesi(int durataMesi) {
		this.durataMesi = durataMesi;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
