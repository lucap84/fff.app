package it.fff.business.service;

import it.fff.business.common.bo.EventBO;

public interface EventBusinessService extends BusinessService{
	
	public EventBO getEvent(int eventId);

}
