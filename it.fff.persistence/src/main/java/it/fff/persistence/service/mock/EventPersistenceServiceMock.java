package it.fff.persistence.service.mock;

import it.fff.business.common.dao.EventDAO;
import it.fff.persistence.service.EventPersistenceService;

public class EventPersistenceServiceMock implements EventPersistenceService{

	@Override
	public EventDAO retrieveEvent(int eventId) {
		EventDAO event = new EventDAO();
		event.setEventId(eventId);
		event.setNome("nome persistente");
		event.setDescrizione("descr persisente");
		return event;
	}

}
