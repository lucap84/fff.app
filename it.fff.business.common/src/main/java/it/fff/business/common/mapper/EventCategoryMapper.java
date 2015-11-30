package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.EventCategoryBO;
import it.fff.business.common.bo.LanguageBO;
import it.fff.business.common.eo.EventCategoryEO;
import it.fff.clientserver.common.dto.EventCategoryDTO;
import it.fff.clientserver.common.dto.LanguageDTO;

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
	

	public static List<EventCategoryDTO> mapBO2DTO(List<EventCategoryBO> bos) {
		List<EventCategoryDTO> dtos = new ArrayList<EventCategoryDTO>();
		if(bos!=null){
			for (EventCategoryBO bo : bos) {
				dtos.add(EventCategoryMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
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

	public static List<EventCategoryBO> mapEOs2BOs(List<EventCategoryEO> eos) {
		List<EventCategoryBO> bos = new ArrayList<EventCategoryBO>();
		if(eos!=null){
			for (EventCategoryEO eo : eos) {
				bos.add(EventCategoryMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}
	
	public static EventCategoryBO mapEO2BO(EventCategoryEO eo) {
		EventCategoryBO bo = new EventCategoryBO();
		bo.setId(eo.getId());
		bo.setNome(eo.getNome());
		bo.setDescrizione(eo.getDescrizione());
		return bo;
	}	
}
