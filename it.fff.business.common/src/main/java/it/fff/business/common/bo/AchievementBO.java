package it.fff.business.common.bo;


public class AchievementBO implements BusinessObject {

	private int	id;
	private String dataOttenimento;
	private AchievementTypeBO type;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDataOttenimento() {
		return dataOttenimento;
	}
	public void setDataOttenimento(String dataOttenimento) {
		this.dataOttenimento = dataOttenimento;
	}
	public AchievementTypeBO getType() {
		return type;
	}
	public void setType(AchievementTypeBO type) {
		this.type = type;
	}
	
}
