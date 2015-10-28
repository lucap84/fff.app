package it.fff.business.facade.service.impl;

import it.business.common.mapper.EventMapper;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.dto.EventDTO;
import it.fff.business.common.dto.GetEventInputDTO;
import it.fff.business.facade.service.BusinessServiceFacade;
import it.fff.business.service.EventBusinessService;
import it.fff.business.util.BusinessServiceProvider;

public class BusinessServiceFacadeImpl implements BusinessServiceFacade{
	
	public BusinessServiceFacadeImpl(){
		
	}

	@Override
	public EventDTO getEvent(GetEventInputDTO getEventDTO) {
		//recupero un bean prototype (non singleton) per avere una nuova istanza ed evitare problemi di concorrenza su operazione di business
		EventBusinessService eventBusinessService = (EventBusinessService)BusinessServiceProvider.getBusinessService("eventBusinessService");
		EventBO eventBO = eventBusinessService.getEvent(getEventDTO.getEventId());
		EventDTO dtoResult = EventMapper.mapBO2DTO(eventBO);
		return dtoResult;
	}


}
