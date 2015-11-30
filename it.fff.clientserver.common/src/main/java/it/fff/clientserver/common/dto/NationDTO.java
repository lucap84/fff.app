package it.fff.clientserver.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NationDTO extends DataTransferObject{
	
	private static final long serialVersionUID = 6302943037577058100L;
	
	private String	id;
	private String nome;
	private String internationalKey;

	@XmlElement public String getId() {
		return id;
	}
	public void setId(String id) {
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
	
	
}
