package it.fff.clientserver.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NationDTO extends DataTransferObject{
	
	private static final long serialVersionUID = 6302943037577058100L;
	
	private int	id;
	private String nome;
	private String internationalKey;
	private String internationalCodeAplha2;
	private String internationalCodeAplha3;

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
	@XmlElement public String getInternationalKey() {
		return internationalKey;
	}
	public void setInternationalKey(String internationalKey) {
		this.internationalKey = internationalKey;
	}
	@XmlElement public String getInternationalCodeAplha2() {
		return internationalCodeAplha2;
	}
	public void setInternationalCodeAplha2(String internationalCodeAplha2) {
		this.internationalCodeAplha2 = internationalCodeAplha2;
	}
	@XmlElement public String getInternationalCodeAplha3() {
		return internationalCodeAplha3;
	}
	public void setInternationalCodeAplha3(String internationalCodeAplha3) {
		this.internationalCodeAplha3 = internationalCodeAplha3;
	}

	
}
