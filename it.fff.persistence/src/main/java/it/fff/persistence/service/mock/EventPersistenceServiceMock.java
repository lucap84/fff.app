package it.fff.persistence.service.mock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.dao.EventDAO;
import it.fff.persistence.service.EventPersistenceService;

public class EventPersistenceServiceMock implements EventPersistenceService{

	private static final Logger logger = LogManager.getLogger(EventPersistenceServiceMock.class);
	
	@Override
	public EventDAO retrieveEvent(int eventId){
		logger.info("retrieveEvent ({})",eventId);
		EventDAO event = new EventDAO();
		event.setEventId(eventId);
		event.setNome("nome persistente");
		event.setDescrizione("descr persisente");
		logger.info("Mocked event ({}) retrieved",eventId);
		return event;
	}

}
