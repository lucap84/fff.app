package it.fff.business.common.eo;

import java.util.Set;

import javax.persistence.CascadeType;
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
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "luogo_keyword", 
		joinColumns = 		 { @JoinColumn(name = "Keyword_ID") }, 
		inverseJoinColumns = { @JoinColumn(name = "Luogo_ID") })
	private Set<PlaceEO> relatedPlaces;	

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

	public Set<PlaceEO> getRelatedPlaces() {
		return relatedPlaces;
	}

	public void setRelatedPlaces(Set<PlaceEO> relatedPlaces) {
		this.relatedPlaces = relatedPlaces;
	}
	
	@Override
	public boolean equals(Object obj) {
		KeywordEO k2 = (KeywordEO)obj;
		return this.id.equals(k2.getId());
	}
	
	@Override
	public int hashCode() {
		return this.token.hashCode();
	}
}
