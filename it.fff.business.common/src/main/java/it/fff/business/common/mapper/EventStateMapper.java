package it.fff.business.common.mapper;

import java.util.ArrayList;
import java.util.List;

import it.fff.business.common.bo.EventStateBO;
import it.fff.business.common.bo.LanguageBO;
import it.fff.business.common.eo.EventStateEO;
import it.fff.clientserver.common.dto.EventStateDTO;
import it.fff.clientserver.common.dto.LanguageDTO;

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


	public static List<EventStateDTO> mapBO2DTO(List<EventStateBO> bos) {
		List<EventStateDTO> dtos = new ArrayList<EventStateDTO>();
		if(bos!=null){
			for (EventStateBO bo : bos) {
				dtos.add(EventStateMapper.mapBO2DTO(bo));
			}
		}
		return dtos;
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


	public static List<EventStateBO> mapEOs2BOs(List<EventStateEO> eos) {
		List<EventStateBO> bos = new ArrayList<EventStateBO>();
		if(eos!=null){
			for (EventStateEO eo : eos) {
				bos.add(EventStateMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}


	public static EventStateBO mapEO2BO(EventStateEO eo) {
		EventStateBO bo = new EventStateBO();
		bo.setId(eo.getId());
		bo.setNome(eo.getNome());
		bo.setDescrizione(eo.getDescrizione());
		return bo;
	}
}
