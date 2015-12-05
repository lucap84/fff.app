package it.fff.business.common.bo;


public class NationBO implements BusinessObject {

	private int	id;
	private String nome;
	private String internationalKey;
	private String internationalCode;
	
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
	public String getInternationalCode() {
		return internationalCode;
	}
	public void setInternationalCode(String internationalCode) {
		this.internationalCode = internationalCode;
	}
	
	
}
