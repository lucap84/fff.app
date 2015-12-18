package it.fff.clientserver.common.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class AchievementDTO {
	
	private int	id;
	private String dataOttenimento;
	private AchievementTypeDTO type;
	
	@XmlElement public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@XmlElement public String getDataOttenimento() {
		return dataOttenimento;
	}
	public void setDataOttenimento(String dataOttenimento) {
		this.dataOttenimento = dataOttenimento;
	}
	@XmlElement public AchievementTypeDTO getType() {
		return type;
	}
	public void setType(AchievementTypeDTO type) {
		this.type = type;
	}
	
	

}
