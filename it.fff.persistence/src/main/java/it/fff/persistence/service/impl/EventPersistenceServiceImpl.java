package it.fff.persistence.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.dao.EventDAO;
import it.fff.persistence.service.EventPersistenceService;
import it.fff.persistence.service.mock.EventPersistenceServiceMock;

public class EventPersistenceServiceImpl implements EventPersistenceService{

	private static final Logger logger = LogManager.getLogger(EventPersistenceServiceMock.class);
	
	@Override
	public EventDAO retrieveEvent(int eventId) throws Exception{
		logger.info("retrieveEvent ({})",eventId);
		EventDAO outputDao = null;
		//		TODO retrieve in DB
		if(outputDao!=null){
			logger.info("Event ({}) retrieved",eventId);
		}
		else{
			throw new Exception("Errore leggendo il DB per ID: "+eventId);
		}
		return outputDao;
	}

}
