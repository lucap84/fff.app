package it.fff.business.facade.service;

import it.fff.business.common.dto.EventDTO;
import it.fff.business.common.dto.IdentifierDTO;
import it.fff.business.facade.exception.BusinessException;

public interface BusinessServiceFacade {
	
//	public EventDTO getEvent(IdentifierDTO getEventInput) throws BusinessException;
	public EventDTO getEvent(int eventId) throws BusinessException;

}
