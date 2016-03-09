package it.fff.business.common.bo;

public class NationBO implements BusinessObject {

	private int	id;
	private String nome;
	private String internationalKey;
	private String internationalCodeAplha2;
	private String internationalCodeAplha3;
	
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
	public String getInternationalKey() {
		return internationalKey;
	}
	public void setInternationalKey(String internationalKey) {
		this.internationalKey = internationalKey;
	}
	public String getInternationalCodeAplha2() {
		return internationalCodeAplha2;
	}
	public void setInternationalCodeAplha2(String internationalCodeAplha2) {
		this.internationalCodeAplha2 = internationalCodeAplha2;
	}
	public String getInternationalCodeAplha3() {
		return internationalCodeAplha3;
	}
	public void setInternationalCodeAplha3(String internationalCodeAplha3) {
		this.internationalCodeAplha3 = internationalCodeAplha3;
	}
	
}
