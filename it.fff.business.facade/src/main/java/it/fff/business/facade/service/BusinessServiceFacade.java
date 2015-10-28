package it.fff.business.facade.service;

import it.fff.business.common.dto.EventDTO;
import it.fff.business.common.dto.GetEventInputDTO;

public interface BusinessServiceFacade {
	
	public EventDTO getEvent(GetEventInputDTO getEventInput); 

}
