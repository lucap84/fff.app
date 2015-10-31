package it.fff.business.common.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder={"userId","nome","cognome"})
public class UserDTO extends DataTransferObject {
	
	private static final long serialVersionUID = -1979200792665108748L;
	private int userId;
	private String nome;
	private String cognome;
	
	public UserDTO(){
		
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	

}
