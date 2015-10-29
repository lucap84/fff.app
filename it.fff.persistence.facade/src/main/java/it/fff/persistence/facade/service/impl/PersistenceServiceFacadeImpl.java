package it.fff.persistence.facade.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.business.common.mapper.EventMapper;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.dao.EventDAO;
import it.fff.persistence.facade.exception.PersistenceException;
import it.fff.persistence.facade.service.PersistenceServiceFacade;
import it.fff.persistence.service.EventPersistenceService;
import it.fff.persistence.util.PersistenceServiceProvider;

public class PersistenceServiceFacadeImpl implements PersistenceServiceFacade{
	
	private static final Logger logger = LogManager.getLogger(PersistenceServiceFacadeImpl.class);

	@Override
	public EventBO retrieveEvent(int eventId) throws PersistenceException{
		logger.debug("retrieving event ({}) ...",eventId);
		//recupero un bean prototype (non singleton) per avere una nuova istanza ed evitare problemi di concorrenza su operazione di persistenza
		EventPersistenceService eventPersistenceService = (EventPersistenceService)PersistenceServiceProvider.getBusinessService("eventPersistenceService");

		EventDAO eventDAO = null;
		try{
			eventDAO = eventPersistenceService.retrieveEvent(eventId);
		}
		catch(Exception e){
			String errMsg = ""+e.getMessage();
			logger.error(errMsg);
			throw new PersistenceException(errMsg,e);
		}
		if(eventDAO!=null){
			logger.debug("event ({}) retrieved",eventId);
		}
		EventMapper mapper = new EventMapper();
		EventBO eventBO = mapper.mapDao2Bo(eventDAO);
		if(eventBO!=null){
			logger.debug("Event ({}) mapped in BO",eventId);
		}
		return eventBO;
	}

}
