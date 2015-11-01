package it.fff.persistence.facade.service;

import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.UserBO;
import it.fff.persistence.facade.exception.PersistenceException;

public interface PersistenceServiceFacade {

	public EventBO retrieveEvent(int eventId) throws PersistenceException;

	public UserBO registerUser(UserBO userBO) throws PersistenceException;
	

}
