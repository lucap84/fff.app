package it.fff.business.common.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "achievement_ottenuto")
public class AchievementObtainedEO extends EntityObject {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer	id;
	
	@Column(name = "Data_Creazione")
	private String dataCreazione;
	
	@ManyToOne
	@JoinColumn(name = "Utente_ID", nullable = false)
	private UserEO user;
	
	@ManyToOne
	@JoinColumn(name = "Achievement_ID", nullable = false) //Unidirezionale
	private AchievementTypeEO type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public UserEO getUser() {
		return user;
	}

	public void setUser(UserEO user) {
		this.user = user;
	}

	public AchievementTypeEO getType() {
		return type;
	}

	public void setType(AchievementTypeEO type) {
		this.type = type;
	}
	
}
