package it.fff.business.common.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "immagine")
public class ProfileImageEO extends EntityObject {

	@Id
	@Column(name="ID")
	@GeneratedValue
	private Integer id;
	
	@Column(name="Filename")
	private String filename;
	
	@Column(name="Extension")
	private String extension;
	
	@Column(name="Size")
	private Long size;
	
	@Column(name="Hash")
	private String hash;
	
	@Column(name="Path")
	private String path;
	
	@Column(name="Flg_Profile")
	private boolean isProfileImage;

	@ManyToOne
	@JoinColumn(name = "Utente_ID", nullable = false)
	private UserEO user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isProfileImage() {
		return isProfileImage;
	}

	public void setProfileImage(boolean isProfileImage) {
		this.isProfileImage = isProfileImage;
	}

	public UserEO getUser() {
		return user;
	}

	public void setUser(UserEO user) {
		this.user = user;
	}

	/*
	 * setter if not empty
	 */
	
	public void setIdIfNotEmpty(int id) {
		if(!isEmpty(id)) setId(id);
	}
	public void setExtensionIfNotEmpty(String extension) {
		if(!isEmpty(extension)) this.setExtension(extension);
	}
	public void setFilenameIfNotEmpty(String fileName) {
		if(!isEmpty(fileName)) this.setFilename(fileName);
	}
	public void setHashIfNotEmpty(String hash) {
		if(!isEmpty(hash)) this.setHash(hash);
	}
	public void setPathIfNotEmpty(String path) {
		if(!isEmpty(path)) this.setPath(path);
	}
	public void setSizeIfNotEmpty(long size) {
		if(!isEmpty(size)) this.setSize(size);
	}
	
}
