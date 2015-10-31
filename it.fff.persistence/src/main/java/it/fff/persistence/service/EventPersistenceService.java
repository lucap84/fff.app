package it.fff.persistence.service;

import java.sql.SQLException;

import it.fff.business.common.dao.EventDAO;

public interface EventPersistenceService extends PersistenceService{
	
	public EventDAO retrieveEvent(int eventId) throws Exception,SQLException;

}
