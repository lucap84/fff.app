package it.fff.business.common.bo;

public class LanguageBO implements BusinessObject {
	
	private int	id;
	private String nome;
	private String iso639_1;
	private String iso639_2;
	private String iso639_3;
	
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
	public String getIso639_1() {
		return iso639_1;
	}
	public void setIso639_1(String iso639_1) {
		this.iso639_1 = iso639_1;
	}
	public String getIso639_2() {
		return iso639_2;
	}
	public void setIso639_2(String iso639_2) {
		this.iso639_2 = iso639_2;
	}
	public String getIso639_3() {
		return iso639_3;
	}
	public void setIso639_3(String iso639_3) {
		this.iso639_3 = iso639_3;
	}
	
}
