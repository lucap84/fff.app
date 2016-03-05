package it.fff.business.service;

import java.util.List;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.clientserver.common.enums.FeedbackEnum;
import it.fff.integration.facade.exception.IntegrationException;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.MessageBO;

public interface EventBusinessService extends BusinessService{
	
	public EventBO getEvent(int eventId) throws  IntegrationException;
	
	public List<AttendanceBO> getAttendancesByEvent(int eventId) throws  IntegrationException;

	public WriteResultBO createEvent(EventBO bo) throws  IntegrationException;

	public WriteResultBO cancelEvent(int eventId, int organizerId) throws  IntegrationException;

	public WriteResultBO postEventMessage(int attendanceId, String message)  throws  IntegrationException;

	public WriteResultBO postStandardEventMessage(int attendanceId, int stdMsgId) throws  IntegrationException;

	public WriteResultBO joinEvent(AttendanceBO bo) throws  IntegrationException;

	public WriteResultBO addFeedback(int attendanceId, FeedbackEnum feedback) throws  IntegrationException;

	public List<MessageBO> getEventMessages(int eventId) throws  IntegrationException;

	public List<EventBO> searchEvents(double userGpsLat, double userGpsLong, double radiusKm, double desideredGpsLat, double desideredGpsLong,int idCategoria, int partecipanti) throws  IntegrationException;

	public List<EventBO> getEventsByUser(int userId) throws  IntegrationException;

}
