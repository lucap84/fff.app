package it.fff.persistence.service;

import java.sql.SQLException;

import it.fff.business.common.eo.EventEO;

public interface EventPersistenceService extends PersistenceService{
	
	public EventEO retrieveEvent(int eventId) throws Exception,SQLException;

}
