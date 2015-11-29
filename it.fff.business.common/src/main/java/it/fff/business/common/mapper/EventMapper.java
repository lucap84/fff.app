package it.fff.business.common.mapper;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.EventBO;
import it.fff.business.common.eo.EventEO;
import it.fff.clientserver.common.dto.EventDTO;

public class EventMapper implements Mapper{
	
	private static final Logger logger = LogManager.getLogger(EventMapper.class);
	
	public EventMapper(){
		
	}

	public static EventBO mapDTO2BO(EventDTO dto) {
		EventBO bo = new EventBO();
		if(dto!=null){
			if(dto.getId()!=null && !"".equals(dto.getId())){
				bo.setId(Integer.valueOf(dto.getId()));
			}
			bo.setTitolo(dto.getTitolo());
			bo.setDescrizione(dto.getDescrizione());
			bo.setDurata(Integer.valueOf(dto.getDurata()));
			bo.setDataInizio(dto.getDataInizio());
			bo.setStato(EventStateMapper.mapDTO2BO(dto.getStato()));
			bo.setLocation(PlaceMapper.mapDTO2BO(dto.getLocation()));
			bo.setMessages(MessageMapper.mapDTO2BO(dto.getMessages()));
			bo.setPartecipazioni(AttendanceMapper.mapDTO2BO(dto.getPartecipazioni()));			
			bo.setCategoria(EventCategoryMapper.mapDTO2BO(dto.getCategoria()));
		}
		else{logger.warn("Mapping null objects!!");}
		return bo;
	}




	public static EventDTO mapBO2DTO(EventBO bo) {
		EventDTO dto = new EventDTO();
		if(bo!=null){
			if(bo.getId()>0){
				dto.setId(String.valueOf(bo.getId()));
			}
			dto.setTitolo(bo.getTitolo());
			dto.setDescrizione(bo.getDescrizione());
			dto.setDurata(String.valueOf(bo.getDurata()));
			dto.setDataInizio(bo.getDataInizio());
			dto.setStato(EventStateMapper.mapBO2DTO(bo.getStato()));
			dto.setLocation(PlaceMapper.mapBO2DTO(bo.getLocation()));
			dto.setMessages(MessageMapper.mapBO2DTO(bo.getMessages()));
			dto.setPartecipazioni(AttendanceMapper.mapBO2DTO(bo.getPartecipazioni()));			
			dto.setCategoria(EventCategoryMapper.mapBO2DTO(bo.getCategoria()));
		}
		else{logger.warn("Mapping null objects!!");}
		return dto;
	}

	public static EventEO mapBO2EO(EventBO bo) {
		logger.debug("mapBo2Eo");
		EventEO eo = new EventEO();
		if(bo!=null){
			eo.setId(bo.getId());
			eo.setTitolo(bo.getTitolo());
			eo.setDescrizione(bo.getDescrizione());
			
		}
		else{logger.warn("Mapping null objects!!");}
		return eo;
	}

	public static EventBO mapEO2BO(EventEO eo) {
		logger.debug("mapEO2BO");
		EventBO bo = new EventBO();
		if(eo!=null){
			bo.setId(eo.getId());
			bo.setTitolo(eo.getTitolo());
			bo.setDescrizione(eo.getDescrizione());
			bo.setDurata(eo.getDurata());
			bo.setPartecipazioni(AttendanceMapper.mapEO2BO(eo.getPartecipazioni()));
			bo.setCategoria(EventCategoryMapper.mapEO2BO(eo.getCategoria()));
		}
		else{logger.warn("Mapping null objects!!");}
		return bo;
	}




	public static List<EventDTO> mapBO2DTO(List<EventBO> bos) {
		List<EventDTO> dtos = new ArrayList<EventDTO>();
		if(bos!=null){
			for (EventBO bo : bos) {
				dtos.add(EventMapper.mapBO2DTO(bo));
			}
		}
		else{logger.warn("Mapping null objects!!");}
		return dtos;
	}

	
	public static List<EventBO> mapEOs2BOs(List<EventEO> eos){
		List<EventBO> bos = new ArrayList<EventBO>();
		if(eos!=null){
			for (EventEO eo : eos) {
				bos.add(EventMapper.mapEO2BO(eo));
			}
		}
		return bos;
	}


}
