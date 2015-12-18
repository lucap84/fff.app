package it.fff.clientserver.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CityDTO extends DataTransferObject {

	private static final long serialVersionUID = 3296851846357818430L;
	
	private int id;
	private String nome;
	private NationDTO nazione;
	
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
	@XmlElement public NationDTO getNazione() {
		return nazione;
	}
	public void setNazione(NationDTO nazione) {
		this.nazione = nazione;
	}
	
	
}
