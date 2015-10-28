package it.fff.persistence.facade.service.impl;

import it.business.common.mapper.EventMapper;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.dao.EventDAO;
import it.fff.persistence.facade.service.PersistenceServiceFacade;
import it.fff.persistence.service.EventPersistenceService;
import it.fff.persistence.util.PersistenceServiceProvider;

public class PersistenceServiceFacadeImpl implements PersistenceServiceFacade{

	@Override
	public EventBO retrieveEvent(int eventId) {
		//recupero un bean prototype (non singleton) per avere una nuova istanza ed evitare problemi di concorrenza su operazione di persistenza
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getBusinessService("eventPersistenceService");

		EventDAO eventDAO = eventPersistenceService.retrieveEvent(eventId);
		
		EventBO eventBO = EventMapper.mapDAO2BO(eventDAO);
		return eventBO;
	}

}
