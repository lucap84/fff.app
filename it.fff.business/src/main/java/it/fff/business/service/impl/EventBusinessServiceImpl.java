package it.fff.business.service.impl;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.common.util.Constants;
import it.fff.business.comparator.PlaceDistanceComparator;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.MessageBO;
import it.fff.business.service.EventBusinessService;
import it.fff.clientserver.common.enums.FeedbackEnum;
import it.fff.integration.facade.exception.IntegrationException;
import it.fff.integration.facade.service.IntegrationServiceFacade;

public class EventBusinessServiceImpl implements EventBusinessService{
	
	private static final Logger logger = LogManager.getLogger(EventBusinessServiceImpl.class);
	
	private IntegrationServiceFacade integrationFacade;

	
	public IntegrationServiceFacade getIntegrationFacade() {
		return integrationFacade;
	}

	public void setIntegrationFacade(IntegrationServiceFacade integrationFacade) {
		this.integrationFacade = integrationFacade;
	}

	@Override
	public List<AttendanceBO> getAttendancesByEvent(int eventId) throws IntegrationException {
		List<AttendanceBO> attendances =integrationFacade.getAttendancesByEvent(eventId);
		return attendances;
	}	
	
	@Override
	public EventBO getEvent(int eventId) throws IntegrationException{
		logger.info("EventBusinessServiceImpl retrieving event...");
		EventBO event = integrationFacade.retrieveEvent(eventId);
		if(event!=null){
			logger.info("EventBusinessServiceImpl retrieved");
		}
		return event;
	}
	
	@Override
	public WriteResultBO createEvent(EventBO bo) throws IntegrationException {
		WriteResultBO resultBO = integrationFacade.createEvent(bo);
		return resultBO;
	}


	@Override
	public WriteResultBO cancelEvent(int eventId, int organizerId) throws IntegrationException {
		WriteResultBO resultBO = integrationFacade.cancelEvent(eventId, organizerId);
		return resultBO;
	}	

	@Override
	public WriteResultBO postEventMessage(int attendanceId, String message) throws IntegrationException {
		WriteResultBO resultBO = integrationFacade.createEventMessage(attendanceId, message);
		return resultBO;
	}

	@Override
	public WriteResultBO postStandardEventMessage(int attendanceId, int stdMsgId) throws IntegrationException {
		WriteResultBO resultBO = integrationFacade.createStandardEventMessage(attendanceId, stdMsgId);
		return resultBO;
	}

	@Override
	public WriteResultBO joinEvent(AttendanceBO bo) throws IntegrationException {
		WriteResultBO resultBO = integrationFacade.joinEvent(bo);
		return resultBO;
	}

	@Override
	public WriteResultBO addFeedback(int attendanceId, FeedbackEnum feedback) throws IntegrationException {
		WriteResultBO resultBO = integrationFacade.addFeedback(attendanceId, feedback);
		return resultBO;
	}

	@Override
	public List<MessageBO> getEventMessages(int eventId) throws IntegrationException {
		List<MessageBO> bos = integrationFacade.getEventMessages(eventId);
		return bos;
	}

	@Override
	public List<EventBO> searchEvents(	double userGpsLat, 
										double userGpsLong, 
										double radiusKm, 
										double desideredGpsLat, 
										double desideredGpsLong,
										int idCategoria, 
										int minPartecipanti) throws IntegrationException {
		
		/*
		 * Calcolo un quadrato come spazio di ricerca invece di un cerchio, per semplicita
		 * Le assunzioni sono prese da: http://gis.stackexchange.com/questions/19760/how-do-i-calculate-the-bounding-box-for-given-a-distance-and-latitude-longitude
		 */
		double diameterKm = radiusKm*2;
		double sideOfSquareKm= diameterKm; //assumo di lavorare con un lato del quadrato
		
		double sideOfSquareDegrees = Constants.ONE_KM_TO_DEGREES*sideOfSquareKm; //trasformo km in gradi 
		
		double gpsLatFrom = userGpsLat-sideOfSquareDegrees;
		double gpsLatTo = userGpsLat+sideOfSquareDegrees;
		double gpsLongFrom = userGpsLong-sideOfSquareDegrees;
		double gpsLongTo = userGpsLong+sideOfSquareDegrees;
		
		List<EventBO> bos = integrationFacade.searchEvents(gpsLatFrom, gpsLatTo, gpsLongFrom, gpsLongTo, idCategoria, minPartecipanti);
		
		PlaceDistanceComparator comparator = new PlaceDistanceComparator(desideredGpsLat, desideredGpsLong);
		Collections.sort(bos, comparator);
		
		return bos;
	}

	@Override
	public List<EventBO> getEventsByUser(int userId) throws IntegrationException {
		List<EventBO> bos = integrationFacade.getEventsByUser(userId);
		return bos;
	}
	
	

}
