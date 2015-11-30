package it.fff.clientserver.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CityDTO extends DataTransferObject {

	private static final long serialVersionUID = 3296851846357818430L;
	
	private String id;
	private String nome;
	private NationDTO nazione;
	
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
	@XmlElement public NationDTO getNazione() {
		return nazione;
	}
	public void setNazione(NationDTO nazione) {
		this.nazione = nazione;
	}
	
	
}