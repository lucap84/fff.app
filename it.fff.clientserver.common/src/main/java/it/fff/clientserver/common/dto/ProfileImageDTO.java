package it.fff.clientserver.common.dto;

import java.io.InputStream;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProfileImageDTO extends DataTransferObject {
	
	private static final long serialVersionUID = 3192121877817654035L;
	private int userId;
	private InputStream imageInputStream;
	private String imageAsB64;
	private String fileName;
	private String name;
	private Map<String,String> parameters;
	private long size;
	private String type;
	private int imgHashCode;
	
	
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
	@XmlElement public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@XmlElement public Map<String, String> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}
	@XmlElement public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@XmlElement public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	@XmlElement public int getImgHashCode() {
		return imgHashCode;
	}
	public void setImgHashCode(int imgHashCode) {
		this.imgHashCode = imgHashCode;
	}
	@XmlElement public String getImageAsB64() {
		return imageAsB64;
	}
	public void setImageAsB64(String imageAsB64) {
		this.imageAsB64 = imageAsB64;
	}
	
	
}
