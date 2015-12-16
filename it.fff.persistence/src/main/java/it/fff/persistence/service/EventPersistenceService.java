package it.fff.persistence.service;

import java.util.List;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.clientserver.common.enums.EventStateEnum;
import it.fff.clientserver.common.enums.FeedbackEnum;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.MessageBO;

public interface EventPersistenceService extends PersistenceService{
	
	public EventBO retrieveEvent(int eventId) throws Exception;

	public WriteResultBO cancelEvent(int eventId, int organizerId) throws Exception;
	
	public WriteResultBO updateEventState(int eventId, EventStateEnum state) throws Exception;

	public WriteResultBO createEvent(EventBO bo) throws Exception;

	public WriteResultBO createEventMessage(int attendanceId, String message) throws Exception;

	public WriteResultBO createStandardEventMessage(int attendanceId, int stdMsgId) throws Exception;

	public WriteResultBO addFeedback(int attendanceId, FeedbackEnum feedback) throws Exception;

	public WriteResultBO createAttandance(AttendanceBO eo) throws Exception;

	public List<AttendanceBO> getAttendancesByEvent(int eventId) throws Exception;

	public List<EventBO> getEventsByUser(int userId) throws Exception;

	public List<EventBO> searchEvents(double gpsLatFrom, double gpsLatTo, double gpsLongFrom,double gpsLongTo, int idCategoria, int minPartecipanti) throws Exception;

	public List<MessageBO> getEventMessages(int eventId) throws Exception;

}
