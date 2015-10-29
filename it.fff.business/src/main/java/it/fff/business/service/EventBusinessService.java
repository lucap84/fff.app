package it.fff.business.service;

import it.fff.business.common.bo.EventBO;
import it.fff.persistence.facade.exception.PersistenceException;

public interface EventBusinessService extends BusinessService{
	
	public EventBO getEvent(int eventId) throws  PersistenceException;

}
