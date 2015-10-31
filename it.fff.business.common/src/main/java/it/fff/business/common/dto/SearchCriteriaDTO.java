package it.fff.business.common.dto;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlType(propOrder={"posizione","categoria","partecipanti"})
public class SearchCriteriaDTO extends DataTransferObject {
	
	private static final long serialVersionUID = -207857158233822226L;
	private int posizione;
	private String categoria;
	private int partecipanti;
	
	public SearchCriteriaDTO(){
	}

	public int getPosizione() {
		return posizione;
	}

	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getPartecipanti() {
		return partecipanti;
	}

	public void setPartecipanti(int partecipanti) {
		this.partecipanti = partecipanti;
	}

	
}
