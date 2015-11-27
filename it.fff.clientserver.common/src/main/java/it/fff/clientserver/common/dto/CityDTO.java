package it.fff.clientserver.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CityDTO extends DataTransferObject {

	private static final long serialVersionUID = 3296851846357818430L;
	
	private String id;
	private String nome;
	private NazioneDTO nazione;
	
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
	@XmlElement public NazioneDTO getNazione() {
		return nazione;
	}
	public void setNazione(NazioneDTO nazione) {
		this.nazione = nazione;
	}
	
	
}
