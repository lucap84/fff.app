package it.fff.business.common.dto;

import java.io.InputStream;
import java.util.Map;

public class ProfileImageDTO extends DataTransferObject {
	
	private int userId;
	private InputStream imageInputStream;
	private String fileName;
	private String name;
	private Map<String,String> parameters;
	private long size;
	private String type;
	private String imgHashCode;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public InputStream getImageInputStream() {
		return imageInputStream;
	}
	public void setImageInputStream(InputStream imageInputStream) {
		this.imageInputStream = imageInputStream;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Map<String, String> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getImgHashCode() {
		return imgHashCode;
	}
	public void setImgHashCode(String imgHashCode) {
		this.imgHashCode = imgHashCode;
	}
	
}
