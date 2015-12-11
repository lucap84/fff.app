package it.fff.business.service;

import java.util.List;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.MessageBO;
import it.fff.persistence.facade.exception.PersistenceException;

public interface EventBusinessService extends BusinessService{
	
	public EventBO getEvent(int eventId) throws  PersistenceException;
	
	public List<AttendanceBO> getAttendancesByEvent(int eventId) throws  PersistenceException;

	public WriteResultBO createEvent(EventBO bo) throws  PersistenceException;

	public WriteResultBO cancelEvent(int eventId, int organizerId) throws  PersistenceException;

	public WriteResultBO postEventMessage(int attendanceId, String message)  throws  PersistenceException;

	public WriteResultBO postStandardEventMessage(int attendanceId, int stdMsgId) throws  PersistenceException;

	public WriteResultBO joinEvent(AttendanceBO bo) throws  PersistenceException;

	public WriteResultBO addFeedback(AttendanceBO bo) throws  PersistenceException;

	public List<MessageBO> getEventMessages(int eventId) throws  PersistenceException;

	public List<EventBO> searchEvents(double userGpsLat, double userGpsLong, double radiusKm, double desideredGpsLat, double desideredGpsLong,int idCategoria, int partecipanti) throws  PersistenceException;

	public List<EventBO> getEventsByUser(int userId) throws  PersistenceException;

}
