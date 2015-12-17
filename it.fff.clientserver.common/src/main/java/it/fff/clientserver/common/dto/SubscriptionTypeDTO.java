package it.fff.clientserver.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SubscriptionTypeDTO extends DataTransferObject {

	private static final long serialVersionUID = -8839952200435157199L;

	private String id;
	private String nome;
	private String durataGiorni;
	private String durataMesi;
	private String descrizione;

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
	@XmlElement public String getDurataGiorni() {
		return durataGiorni;
	}
	public void setDurataGiorni(String durataGiorni) {
		this.durataGiorni = durataGiorni;
	}
	@XmlElement public String getDurataMesi() {
		return durataMesi;
	}
	public void setDurataMesi(String durataMesi) {
		this.durataMesi = durataMesi;
	}
	@XmlElement public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
