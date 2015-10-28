package it.business.common.mapper;

import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.GetEventInputBO;
import it.fff.business.common.dao.EventDAO;
import it.fff.business.common.dto.EventDTO;
import it.fff.business.common.dto.GetEventInputDTO;

public class EventMapper {

	public static EventBO mapDTO2BO(EventDTO dto){
		EventBO bo = new EventBO();
		bo.setEventId(dto.getEventId());
		bo.setNome(dto.getNome());
		bo.setDescrizione(dto.getDescrizione());
		return bo;
	}
	
	public static EventDTO mapBO2DTO(EventBO bo){
		EventDTO dto = new EventDTO();
		dto.setEventId(bo.getEventId());
		dto.setNome(bo.getNome());
		dto.setDescrizione(bo.getDescrizione());
		return dto;
	}
	
	public static EventDAO mapBO2DAO(EventBO bo){
		EventDAO dao = new EventDAO();
		dao.setEventId(bo.getEventId());
		dao.setNome(bo.getNome());
		dao.setDescrizione(bo.getDescrizione());
		return dao;
	}
	
	public static EventBO mapDAO2BO(EventDAO dao){
		EventBO bo = new EventBO();
		bo.setEventId(dao.getEventId());
		bo.setNome(dao.getNome());
		bo.setDescrizione(dao.getDescrizione());
		return bo;
	}	
}
