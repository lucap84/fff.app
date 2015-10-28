package it.business.service.impl;

import it.fff.business.common.bo.EventBO;
import it.fff.business.service.EventBusinessService;
import it.fff.persistence.facade.service.PersistenceServiceFacade;

public class EventBusinessServiceImpl implements EventBusinessService{
	
	private PersistenceServiceFacade persistenceFacade;

	@Override
	public EventBO getEvent(int eventId) {
		
		EventBO event = persistenceFacade.retrieveEvent(eventId);
		
		return event;
	}

	public PersistenceServiceFacade getPersistenceFacade() {
		return persistenceFacade;
	}

	public void setPersistenceFacade(PersistenceServiceFacade persistenceFacade) {
		this.persistenceFacade = persistenceFacade;
	}
	
	

}
