package it.fff.business.common.eo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "keyword")
public class KeywordEO extends EntityObject {

	@Id
	@Column(name="ID")
	@GeneratedValue
	private Integer id;

	@Column(name="Token")
	private String token;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "luogo_keyword", 
		joinColumns = 		 { @JoinColumn(name = "Keyword_ID") }, 
		inverseJoinColumns = { @JoinColumn(name = "Luogo_ID") })
	private List<PlaceEO> relatedPlaces;	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<PlaceEO> getRelatedPlaces() {
		return relatedPlaces;
	}

	public void setRelatedPlaces(List<PlaceEO> relatedPlaces) {
		this.relatedPlaces = relatedPlaces;
	}
	
}
