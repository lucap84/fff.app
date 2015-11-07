package it.fff.persistence.service.impl;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.eo.EventEO;
import it.fff.persistence.service.EventPersistenceService;
import it.fff.persistence.service.mock.EventPersistenceServiceMock;

public class EventPersistenceServiceImpl implements EventPersistenceService{

	private static final Logger logger = LogManager.getLogger(EventPersistenceServiceMock.class);
	
	@Override
	public EventEO retrieveEvent(int eventId) throws SQLException{
		logger.info("retrieveEvent ({})",eventId);
		EventEO outputEo = null;
		//		TODO retrieve in DB
		if(outputEo!=null){
			logger.info("Event ({}) retrieved",eventId);
		}
		else{
			throw new SQLException("Errore leggendo il DB per ID: "+eventId);
		}
		return outputEo;
	}

}
