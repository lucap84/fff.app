package it.business.common.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.EventBO;
import it.fff.business.common.dao.EventDAO;
import it.fff.business.common.dto.EventDTO;

public class EventMapper implements BeanMapper<EventDTO,EventBO,EventDAO>{
	
	private static final Logger logger = LogManager.getLogger(EventMapper.class);
	
	public EventMapper(){
		
	}

	@Override
	public EventBO mapDto2Bo(EventDTO dto) {
		logger.debug("mapDto2Bo");
		EventBO bo = new EventBO();
		bo.setEventId(dto.getEventId());
		bo.setNome(dto.getNome());
		bo.setDescrizione(dto.getDescrizione());
		return bo;
	}

	@Override
	public EventDTO mapBo2Dto(EventBO bo) {
		logger.debug("mapBo2Dto");
		EventDTO dto = new EventDTO();
		dto.setEventId(bo.getEventId());
		dto.setNome(bo.getNome());
		dto.setDescrizione(bo.getDescrizione());
		return dto;
	}

	@Override
	public EventDAO mapBo2Dao(EventBO bo) {
		logger.debug("mapBo2Dao");
		EventDAO dao = new EventDAO();
		dao.setEventId(bo.getEventId());
		dao.setNome(bo.getNome());
		dao.setDescrizione(bo.getDescrizione());
		return dao;
	}

	@Override
	public EventBO mapDao2Bo(EventDAO dao) {
		logger.debug("mapDAO2BO");
		EventBO bo = new EventBO();
		bo.setEventId(dao.getEventId());
		bo.setNome(dao.getNome());
		bo.setDescrizione(dao.getDescrizione());
		return bo;
	}



}
