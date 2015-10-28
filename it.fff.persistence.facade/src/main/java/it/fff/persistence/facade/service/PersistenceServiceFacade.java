package it.fff.persistence.facade.service;

import it.fff.business.common.bo.EventBO;

public interface PersistenceServiceFacade {

	public EventBO retrieveEvent(int eventId);
	

}
