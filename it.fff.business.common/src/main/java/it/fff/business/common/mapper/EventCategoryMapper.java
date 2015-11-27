package it.fff.business.common.mapper;

import it.fff.business.common.bo.EventCategoryBO;
import it.fff.business.common.eo.EventCategoryEO;
import it.fff.clientserver.common.dto.EventCategoryDTO;

public class EventCategoryMapper implements Mapper{

	public static EventCategoryBO mapDTO2BO(EventCategoryDTO dto) {
		EventCategoryBO bo = new EventCategoryBO();
		if(dto.getId()!=null && !"".equals(dto.getId())){
			bo.setId(Integer.valueOf(dto.getId()));
		}
		bo.setNome(dto.getNome());
		bo.setDescrizione(dto.getDescrizione());
		return bo;
	}
	
	public static EventCategoryBO mapEO2BO(EventCategoryEO eo) {
		EventCategoryBO bo = new EventCategoryBO();
		bo.setId(eo.getId());
		bo.setNome(eo.getNome());
		bo.setDescrizione(eo.getDescrizione());
		return bo;
	}

	public static EventCategoryDTO mapBO2DTO(EventCategoryBO bo) {
		EventCategoryDTO dto = new EventCategoryDTO();
		if(bo.getId()>0){
			dto.setId(String.valueOf(bo.getId()));
		}
		dto.setNome(bo.getNome());
		dto.setDescrizione(bo.getDescrizione());
		return dto;
	}	
}
