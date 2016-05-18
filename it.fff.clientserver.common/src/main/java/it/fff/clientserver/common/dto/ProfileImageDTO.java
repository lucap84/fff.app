package it.fff.clientserver.common.dto;

import java.io.InputStream;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProfileImageDTO extends DataTransferObject {
	
	private static final long serialVersionUID = 89254415294464335L;
	
	private int id;
	private String fileName;
	private String extension;
	private long size;
	private String hash;
	private String path;
	private boolean isProfileImage;
	private int userId;

	private InputStream imageInputStream;
	private String imageAsB64;
	private String name;
	private Map<String,String> parameters;
	
	@XmlElement public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@XmlElement public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@XmlElement public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	@XmlElement public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	@XmlElement public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	@XmlElement public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@XmlElement public boolean isProfileImage() {
		return isProfileImage;
	}
	public void setProfileImage(boolean isProfileImage) {
		this.isProfileImage = isProfileImage;
	}
	@XmlElement public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@XmlElement public InputStream getImageInputStream() {
		return imageInputStream;
	}
	public void setImageInputStream(InputStream imageInputStream) {
		this.imageInputStream = imageInputStream;
	}
	@XmlElement public String getImageAsB64() {
		return imageAsB64;
	}
	public void setImageAsB64(String imageAsB64) {
		this.imageAsB64 = imageAsB64;
	}
	@XmlElement public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement public Map<String, String> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}
	
	
}
