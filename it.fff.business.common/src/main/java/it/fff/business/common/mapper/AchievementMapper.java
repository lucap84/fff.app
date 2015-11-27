package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.AchievementBO;
import it.fff.business.common.eo.AchievementEO;

public class AchievementMapper implements Mapper{

	public static List<AchievementBO> mapEO2BO(List<AchievementEO> eos) {
		List<AchievementBO> bos = new ArrayList<AchievementBO>();
		if(eos!=null){
			for (AchievementEO eo : eos) {
				bos.add(AchievementMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}
	
	public static AchievementBO mapEO2BO(AchievementEO eo) {
		AchievementBO bo = new AchievementBO();
		if(eo!=null){
			bo.setId(eo.getId());
			bo.setNome(eo.getDescrizione());
			bo.setDescrizione(eo.getDescrizione());
		}
		return bo;
	}
}
