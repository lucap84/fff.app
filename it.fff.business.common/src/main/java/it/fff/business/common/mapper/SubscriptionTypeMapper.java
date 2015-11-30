package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.SubscriptionTypeBO;
import it.fff.business.common.eo.SubscriptionTypeEO;
import it.fff.clientserver.common.dto.AchievementTypeDTO;
import it.fff.clientserver.common.dto.SubscriptionTypeDTO;

public class SubscriptionTypeMapper implements Mapper{

	public static List<SubscriptionTypeDTO> mapBO2DTO(List<SubscriptionTypeBO> bos) {
		List<SubscriptionTypeDTO> dtos = new ArrayList<SubscriptionTypeDTO>();
		if(bos!=null){
			for (SubscriptionTypeBO bo : bos) {
				dtos.add(SubscriptionTypeMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
	}

	public static SubscriptionTypeDTO mapBO2DTO(SubscriptionTypeBO bo) {
		SubscriptionTypeDTO dto = new SubscriptionTypeDTO();
		if(bo!=null){
			if(bo.getId()>0){
				dto.setId(String.valueOf(bo.getId()));
			}
			dto.setNome(bo.getNome());
			dto.setDescrizione(bo.getDescrizione());
		}
		return dto;
	}

	public static List<SubscriptionTypeBO> mapEOs2BOs(List<SubscriptionTypeEO> eos) {
		List<SubscriptionTypeBO> bos = new ArrayList<SubscriptionTypeBO>();
		if(bos!=null){
			for (SubscriptionTypeEO eo : eos) {
				bos.add(SubscriptionTypeMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}

	public static SubscriptionTypeBO mapEO2BO(SubscriptionTypeEO eo) {
		SubscriptionTypeBO bo = new SubscriptionTypeBO();
		bo.setId(eo.getId());
		bo.setNome(eo.getNome());
		bo.setDescrizione(eo.getDescrizione());
		return bo;
	}

}
