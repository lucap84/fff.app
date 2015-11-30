package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.AchievementBO;
import it.fff.business.common.bo.AchievementTypeBO;
import it.fff.business.common.eo.AchievementObtainedEO;
import it.fff.business.common.eo.AchievementTypeEO;

public class AchievementMapper implements Mapper{

	public static List<AchievementBO> mapEOs2BOs(List<AchievementObtainedEO> eos) {
		List<AchievementBO> bos = new ArrayList<AchievementBO>();
		if(eos!=null){
			for (AchievementObtainedEO eo : eos) {
				bos.add(AchievementMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}

	
	public static AchievementBO mapEO2BO(AchievementObtainedEO eo) {
		AchievementBO bo = new AchievementBO();
		if(eo!=null){
			bo.setId(eo.getId());
			bo.setDataOttenimento(eo.getDataCreazione());
			bo.setType(AchievementTypeMapper.mapEO2BO(eo.getType()));
		}
		return bo;
	}

}
