package it.fff.persistence.service;

import java.util.List;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.EventCategoryBO;
import it.fff.business.common.bo.MessageStandardBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.clientserver.common.enums.EventStateEnum;
import it.fff.clientserver.common.enums.FeedbackEnum;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.MessageBO;
import it.fff.persistence.exception.PersistenceException;

public interface EventPersistenceService extends PersistenceService{
	
	public EventBO retrieveEvent(int eventId) throws PersistenceException;

	public WriteResultBO cancelEvent(int eventId, int organizerId) throws PersistenceException;
	
	public WriteResultBO updateEventState(int eventId, EventStateEnum state) throws PersistenceException;

	public WriteResultBO createEvent(EventBO bo) throws PersistenceException;

	public WriteResultBO createEventMessage(int attendanceId, String message) throws PersistenceException;

	public WriteResultBO createStandardEventMessage(int attendanceId, int stdMsgId) throws PersistenceException;

	public MessageStandardBO getStandardMessageById(int stdMsgId) throws PersistenceException;

	public WriteResultBO addFeedback(int attendanceId, FeedbackEnum feedback) throws PersistenceException;

	public WriteResultBO createAttandance(AttendanceBO eo) throws PersistenceException;

	public List<AttendanceBO> getAttendancesByEvent(int eventId) throws PersistenceException;

	public List<EventBO> getEventsByUser(int userId) throws PersistenceException;

	public List<EventBO> searchEvents(double gpsLatFrom, double gpsLatTo, double gpsLongFrom,double gpsLongTo, int idCategoria, int minPartecipanti) throws PersistenceException;

	public List<MessageBO> getEventMessages(int eventId) throws PersistenceException;

	public EventCategoryBO getEventCategoryById(int eventCategoryId) throws PersistenceException;

}
