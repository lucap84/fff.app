package it.fff.business.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.MessageBO;
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
	public WriteResultBO createEvent(EventBO bo) throws PersistenceException {
		WriteResultBO resultBO = persistenceFacade.createEvent(bo);
		return resultBO;
	}


	@Override
	public WriteResultBO cancelEvent(int eventId, int organizerId) throws PersistenceException {
		WriteResultBO resultBO = persistenceFacade.cancelEvent(eventId, organizerId);
		return resultBO;
	}	

	@Override
	public WriteResultBO cancelAttendance(int eventId, int attendanceId) throws PersistenceException {
		WriteResultBO resultBO = persistenceFacade.cancelAttendance(eventId, attendanceId);
		return resultBO;
	}

	@Override
	public WriteResultBO postEventMessage(int attendanceId, String message) throws PersistenceException {
		WriteResultBO resultBO = persistenceFacade.createEventMessage(attendanceId, message);
		return resultBO;
	}

	@Override
	public WriteResultBO postStandardEventMessage(int attendanceId, int stdMsgId) throws PersistenceException {
		WriteResultBO resultBO = persistenceFacade.createStandardEventMessage(attendanceId, stdMsgId);
		return resultBO;
	}

	@Override
	public WriteResultBO joinEvent(AttendanceBO bo) throws PersistenceException {
		WriteResultBO resultBO = persistenceFacade.joinEvent(bo);
		return resultBO;
	}

	@Override
	public WriteResultBO addFeedback(AttendanceBO bo) throws PersistenceException {
		WriteResultBO resultBO = persistenceFacade.addFeedback(bo);
		return resultBO;
	}

	@Override
	public List<MessageBO> getEventMessages(int eventId) throws PersistenceException {
		List<MessageBO> bos = persistenceFacade.getEventMessages(eventId);
		return bos;
	}

	@Override
	public List<EventBO> searchEvents(double gpsLat, double gpsLong, int idCategoria, int minPartecipanti) throws PersistenceException {
		double range= 2.0;
		
		double gpsLatFrom = gpsLat-range;
		double gpsLatTo = gpsLat+range;
		double gpsLongFrom = gpsLong-range;
		double gpsLongTo = gpsLong+range;
		
		List<EventBO> bos = persistenceFacade.searchEvents(gpsLatFrom, gpsLatTo, gpsLongFrom, gpsLongTo, idCategoria, minPartecipanti);
		return bos;
	}

	@Override
	public List<EventBO> getEventsByUser(int userId) throws PersistenceException {
		List<EventBO> bos = persistenceFacade.getEventsByUser(userId);
		return bos;
	}
	
	

}
