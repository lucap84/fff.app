package it.fff.business.common.mapper;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.eo.AttendanceEO;
import it.fff.business.common.eo.EventEO;
import it.fff.clientserver.common.dto.EventDTO;

public class EventMapper implements Mapper{
	
	private static final Logger logger = LogManager.getLogger(EventMapper.class);
	
	public EventMapper(){
		
	}

	public static EventBO map2BO(EventDTO dto) {
		logger.debug("mapDto2Bo");
		EventBO bo = new EventBO();
		if(dto!=null){
			if(dto.getId()!=null && !"".equals(dto.getId())){
				bo.setId(Integer.valueOf(dto.getId()));
			}
			bo.setNome(dto.getNome());
			bo.setDescrizione(dto.getDescrizione());
		}
		else{logger.warn("Mapping null objects!!");}
		return bo;
	}

	public static EventDTO map2DTO(EventBO bo) {
		logger.debug("mapBo2Dto");
		EventDTO dto = new EventDTO();
		if(bo!=null){
			dto.setId(String.valueOf(bo.getId()));
			dto.setNome(bo.getNome());
			dto.setDescrizione(bo.getDescrizione());
		}
		else{logger.warn("Mapping null objects!!");}
		return dto;
	}

	public static EventEO map2EO(EventBO bo) {
		logger.debug("mapBo2Eo");
		EventEO eo = new EventEO();
		if(bo!=null){
			eo.setId(bo.getId());
			eo.setNome(bo.getNome());
			eo.setDescrizione(bo.getDescrizione());
		}
		else{logger.warn("Mapping null objects!!");}
		return eo;
	}

	public static EventBO map2BO(EventEO eo) {
		logger.debug("mapEO2BO");
		EventBO bo = new EventBO();
		if(eo!=null){
			bo.setId(eo.getId());
			bo.setNome(eo.getNome());
			bo.setDescrizione(eo.getDescrizione());
		}
		else{logger.warn("Mapping null objects!!");}
		return bo;
	}

	public static List<EventDTO> map2DTO(List<EventBO> bos) {
		List<EventDTO> dtos = new ArrayList<EventDTO>();
		if(bos!=null){
			for (EventBO bo : bos) {
				dtos.add(EventMapper.map2DTO(bo));
			}
		}
		else{logger.warn("Mapping null objects!!");}
		return dtos;
	}

	public static AttendanceEO map2EO(AttendanceBO bo) {
		AttendanceEO eo = new AttendanceEO();
		if(bo!=null){
			if(bo.getId()>0){
				eo.setId(bo.getId());
			}
			eo.setNumPartecipanti(bo.getNumPartecipanti());
			eo.setStatusId(bo.getStatusId());
			eo.setValid(bo.isValid());
			eo.setOrganizer(bo.isOrganizer());
		}
		else{logger.warn("Mapping null objects!!");}
		return eo;
	}
	
	public static List<EventBO> map2BO(List<EventEO> eos){
		List<EventBO> bos = new ArrayList<EventBO>();
		if(eos!=null){
			for (EventEO eo : eos) {
				bos.add(EventMapper.map2DTO(eo));
			}
		}
		return bos;
	}

	public static EventBO map2DTO(EventEO eo) {
		EventBO bo = new EventBO();
		if(eo!=null){
			bo.setId(eo.getId());
			bo.setNome(eo.getNome());
			bo.setDescrizione(eo.getDescrizione());
		}
		else{logger.warn("Mapping null objects!!");}
		return bo;
	}	


}
