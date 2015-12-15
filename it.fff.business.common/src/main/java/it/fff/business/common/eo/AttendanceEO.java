package it.fff.business.common.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "partecipazione")
public class AttendanceEO extends EntityObject {

	@Id
	@Column(name="ID")
	@GeneratedValue
	private Integer id;
	
	@Column(name="Flg_Organizzatore")
	private boolean isOrganizer;
	
	@Column(name="Ospiti")
	private int numPartecipanti;
	
	@Column(name="Flg_Feedback")
	private Boolean isPositiveFeedback;

	@Column(name="Data_Creazione")
	private String dataCreazione;

	@Column(name="Data_Aggiornamento")
	private String dataAggiornamento;
	
	@Column(name="Flg_Validita")
	private boolean isValid;
	
	@Column(name="Count_Aggiornamento")
	private int countAggiornamento;
	
	@ManyToOne
	@JoinColumn(name = "Evento_ID", nullable = false)
	private EventEO event;
	
	@ManyToOne
	@JoinColumn(name = "Utente_ID", nullable = false)
	private UserEO utente;
	
	@ManyToOne
	@JoinColumn(name = "Stato_ID", nullable = false)
	private AttendanceStateEO stato;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isOrganizer() {
		return isOrganizer;
	}

	public void setOrganizer(boolean isOrganizer) {
		this.isOrganizer = isOrganizer;
	}

	public int getNumPartecipanti() {
		return numPartecipanti;
	}

	public void setNumPartecipanti(int numPartecipanti) {
		this.numPartecipanti = numPartecipanti;
	}

	public Boolean isPositiveFeedback() {
		return isPositiveFeedback;
	}

	public void setPositiveFeedback(Boolean isPositiveFeedback) {
		this.isPositiveFeedback = isPositiveFeedback;
	}

	public String getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public String getDataAggiornamento() {
		return dataAggiornamento;
	}

	public void setDataAggiornamento(String dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public int getCountAggiornamento() {
		return countAggiornamento;
	}

	public void setCountAggiornamento(int countAggiornamento) {
		this.countAggiornamento = countAggiornamento;
	}

	public EventEO getEvent() {
		return event;
	}

	public void setEvent(EventEO event) {
		this.event = event;
	}

	public UserEO getUtente() {
		return utente;
	}

	public void setUtente(UserEO utente) {
		this.utente = utente;
	}

	public AttendanceStateEO getStato() {
		return stato;
	}

	public void setStato(AttendanceStateEO stato) {
		this.stato = stato;
	}

	public void setIdIfNotEmpty(int id) {
		if(!isEmpty(id)) this.id = id;
	}

	public void setNumPartecipantiIfNotEmpty(int numPartecipanti) {
		if(!isEmpty(numPartecipanti)) this.numPartecipanti = numPartecipanti;
	}
	
}
