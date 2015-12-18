package it.fff.clientserver.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LanguageDTO extends DataTransferObject{

	private static final long serialVersionUID = 8981419652322518045L;
	
	private int	id;
	private String nome;
	private String iso639_1;
	private String iso639_2;
	private String iso639_3;
	
	@XmlElement public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@XmlElement public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@XmlElement public String getIso639_1() {
		return iso639_1;
	}
	public void setIso639_1(String iso639_1) {
		this.iso639_1 = iso639_1;
	}
	@XmlElement public String getIso639_2() {
		return iso639_2;
	}
	public void setIso639_2(String iso639_2) {
		this.iso639_2 = iso639_2;
	}
	@XmlElement public String getIso639_3() {
		return iso639_3;
	}
	public void setIso639_3(String iso639_3) {
		this.iso639_3 = iso639_3;
	}
	
}
