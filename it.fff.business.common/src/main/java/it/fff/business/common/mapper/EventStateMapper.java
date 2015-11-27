package it.fff.business.common.mapper;

import it.fff.business.common.bo.EventStateBO;
import it.fff.clientserver.common.dto.EventStateDTO;

public class EventStateMapper implements Mapper{
	
	public static EventStateBO mapDTO2BO(EventStateDTO dto) {
		EventStateBO bo = new EventStateBO();
		if(dto.getId()!=null && !"".equals(dto.getId())){
			bo.setId(Integer.valueOf(dto.getId()));
		}
		bo.setNome(dto.getNome());
		bo.setDescrizione(dto.getDescrizione());
		return bo;
	}

	public static EventStateDTO mapBO2DTO(EventStateBO bo) {
		EventStateDTO dto = new EventStateDTO();
		if(bo.getId()>0){
			dto.setId(String.valueOf(bo.getId()));
		}
		dto.setNome(bo.getNome());
		dto.setDescrizione(bo.getDescrizione());
		return dto;
	}
}
