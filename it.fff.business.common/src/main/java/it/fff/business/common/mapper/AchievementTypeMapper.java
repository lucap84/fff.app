package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.AchievementTypeBO;
import it.fff.business.common.eo.AchievementTypeEO;
import it.fff.clientserver.common.dto.AchievementTypeDTO;
import it.fff.clientserver.common.dto.LanguageDTO;

public class AchievementTypeMapper {

	public static List<AchievementTypeDTO> mapBO2DTO(List<AchievementTypeBO> bos) {
		List<AchievementTypeDTO> dtos = new ArrayList<AchievementTypeDTO>();
		if(bos!=null){
			for (AchievementTypeBO bo : bos) {
				dtos.add(AchievementTypeMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
	}

	public static AchievementTypeDTO mapBO2DTO(AchievementTypeBO bo) {
		AchievementTypeDTO dto = new AchievementTypeDTO();
		if(bo!=null){
			if(bo.getId()>0){
				dto.setId(String.valueOf(bo.getId()));
			}
			dto.setNome(bo.getNome());
			dto.setDescrizione(bo.getDescrizione());
		}
		return dto;
	}

	public static List<AchievementTypeBO> mapEOs2BOs(List<AchievementTypeEO> eos) {
		List<AchievementTypeBO> bos = new ArrayList<AchievementTypeBO>();
		if(eos!=null){
			for (AchievementTypeEO eo : eos) {
				bos.add(AchievementTypeMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}

	public static AchievementTypeBO mapEO2BO(AchievementTypeEO eo) {
		AchievementTypeBO bo = new AchievementTypeBO();
		bo.setId(eo.getId());
		bo.setNome(eo.getNome());
		bo.setDescrizione(eo.getDescrizione());
		return bo;
	}

}
