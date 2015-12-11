package it.fff.business.service.impl;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.comparator.PlaceDistanceComparator;
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
	public List<EventBO> searchEvents(	double userGpsLat, 
										double userGpsLong, 
										double radiusKm, 
										double desideredGpsLat, 
										double desideredGpsLong,
										int idCategoria, 
										int minPartecipanti) throws PersistenceException {
		
		/*
		 * Calcolo un quadrato come spazio di ricerca invece di un cerchio, per semplicita
		 * Le assunzioni sono prese da: http://gis.stackexchange.com/questions/19760/how-do-i-calculate-the-bounding-box-for-given-a-distance-and-latitude-longitude
		 */
		double oneKm2Degrees = 0.0089982311916;
		double diameterKm = radiusKm*2;
		double sideOfSquareKm= diameterKm; //assumo di lavorare con un lato del quadrato
		
		double sideOfSquareDegrees = oneKm2Degrees*sideOfSquareKm; //trasformo km in gradi 
		
		double gpsLatFrom = userGpsLat-sideOfSquareDegrees;
		double gpsLatTo = userGpsLat+sideOfSquareDegrees;
		double gpsLongFrom = userGpsLong-sideOfSquareDegrees;
		double gpsLongTo = userGpsLong+sideOfSquareDegrees;
		
		List<EventBO> bos = persistenceFacade.searchEvents(gpsLatFrom, gpsLatTo, gpsLongFrom, gpsLongTo, idCategoria, minPartecipanti);
		
		PlaceDistanceComparator comparator = new PlaceDistanceComparator(desideredGpsLat, desideredGpsLong);
		Collections.sort(bos, comparator);
		
		return bos;
	}

	@Override
	public List<EventBO> getEventsByUser(int userId) throws PersistenceException {
		List<EventBO> bos = persistenceFacade.getEventsByUser(userId);
		return bos;
	}
	
	

}
