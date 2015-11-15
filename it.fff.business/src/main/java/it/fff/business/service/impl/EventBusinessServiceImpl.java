package it.fff.business.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.MessageBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.service.EventBusinessService;
import it.fff.persistence.facade.exception.PersistenceException;
import it.fff.persistence.facade.service.PersistenceServiceFacade;

public class EventBusinessServiceImpl implements EventBusinessService{
	
	private static final Logger logger = LogManager.getLogger(EventBusinessServiceImpl.class);
	
	private PersistenceServiceFacade persistenceFacade;

	public PersistenceServiceFacade getPersistenceFacade() {
		return persistenceFacade;
	}

	public void setPersistenceFacade(PersistenceServiceFacade persistenceFacade) {
		this.persistenceFacade = persistenceFacade;
	}	
	
	@Override
	public List<AttendanceBO> getAttendancesByEvent(int eventId) throws PersistenceException {
		List<AttendanceBO> attendances =persistenceFacade.getAttendancesByEvent(eventId);
		return attendances;
	}	
	
	@Override
	public EventBO getEvent(int eventId) throws PersistenceException{
		logger.info("EventBusinessServiceImpl retrieving event...");
		EventBO event = persistenceFacade.retrieveEvent(eventId);
		if(event!=null){
			logger.info("EventBusinessServiceImpl retrieved");
		}
		return event;
	}
	
	@Override
	public CreateResultBO createEvent(EventBO bo) throws PersistenceException {
		CreateResultBO resultBO = persistenceFacade.createEvent(bo);
		return resultBO;
	}


	@Override
	public UpdateResultBO cancelEvent(int eventId) throws PersistenceException {
		UpdateResultBO resultBO = persistenceFacade.cancelEvent(eventId);
		return resultBO;
	}	

	@Override
	public UpdateResultBO cancelAttendance(int eventId, int attendanceId) throws PersistenceException {
		UpdateResultBO resultBO = persistenceFacade.cancelAttendance(eventId, attendanceId);
		return resultBO;
	}

	@Override
	public CreateResultBO postEventMessage(int attendanceId, String message) throws PersistenceException {
		CreateResultBO resultBO = persistenceFacade.createEventMessage(attendanceId, message);
		return resultBO;
	}

	@Override
	public CreateResultBO postStandardEventMessage(int attendanceId, int stdMsgId) throws PersistenceException {
		CreateResultBO resultBO = persistenceFacade.createStandardEventMessage(attendanceId, stdMsgId);
		return resultBO;
	}

	@Override
	public CreateResultBO joinEvent(AttendanceBO bo) throws PersistenceException {
		CreateResultBO resultBO = persistenceFacade.joinEvent(bo);
		return resultBO;
	}

	@Override
	public CreateResultBO addFeedback(AttendanceBO bo) throws PersistenceException {
		CreateResultBO resultBO = persistenceFacade.addFeedback(bo);
		return resultBO;
	}

	@Override
	public List<MessageBO> getEventMessages(int eventId) throws PersistenceException {
		List<MessageBO> bos = persistenceFacade.getEventMessages(eventId);
		return bos;
	}

	@Override
	public List<EventBO> searchEvents(double gpsLat, double gpsLong, int idCategoria, int partecipanti) throws PersistenceException {
		List<EventBO> bos = persistenceFacade.searchEvents(gpsLat, gpsLong, idCategoria, partecipanti);
		return bos;
	}

	@Override
	public List<EventBO> getEventsByUser(int userId) throws PersistenceException {
		List<EventBO> bos = persistenceFacade.getEventsByUser(userId);
		return bos;
	}
	
	

}
