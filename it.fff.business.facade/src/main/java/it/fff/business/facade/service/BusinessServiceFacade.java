package it.fff.business.facade.service;

import it.fff.business.common.dto.EventDTO;
import it.fff.business.common.dto.GetEventInputDTO;
import it.fff.business.facade.exception.BusinessException;

public interface BusinessServiceFacade {
	
	public EventDTO getEvent(GetEventInputDTO getEventInput) throws BusinessException; 

}
