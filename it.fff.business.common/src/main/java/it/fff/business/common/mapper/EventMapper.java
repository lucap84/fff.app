package it.fff.business.common.mapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.EventBO;
import it.fff.business.common.eo.EventEO;
import it.fff.clientserver.common.dto.EventDTO;

public class EventMapper implements BeanMapper<EventDTO,EventBO,EventEO>{
	
	private static final Logger logger = LogManager.getLogger(EventMapper.class);
	
	public EventMapper(){
		
	}

	@Override
	public EventBO mapDto2Bo(EventDTO dto) {
		logger.debug("mapDto2Bo");
		EventBO bo = new EventBO();
		bo.setEventId(Integer.valueOf(dto.getEventId()));
		bo.setNome(dto.getNome());
		bo.setDescrizione(dto.getDescrizione());
		return bo;
	}

	@Override
	public EventDTO mapBo2Dto(EventBO bo) {
		logger.debug("mapBo2Dto");
		EventDTO dto = new EventDTO();
		dto.setEventId(String.valueOf(bo.getEventId()));
		dto.setNome(bo.getNome());
		dto.setDescrizione(bo.getDescrizione());
		return dto;
	}

	@Override
	public EventEO mapBo2Eo(EventBO bo) {
		logger.debug("mapBo2Eo");
		EventEO eo = new EventEO();
		eo.setEventId(bo.getEventId());
		eo.setNome(bo.getNome());
		eo.setDescrizione(bo.getDescrizione());
		return eo;
	}

	@Override
	public EventBO mapEo2Bo(EventEO eo) {
		logger.debug("mapEO2BO");
		EventBO bo = new EventBO();
		bo.setEventId(eo.getEventId());
		bo.setNome(eo.getNome());
		bo.setDescrizione(eo.getDescrizione());
		return bo;
	}



}
