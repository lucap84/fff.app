package it.fff.persistence.service;

import java.util.List;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.MessageBO;
import it.fff.business.common.bo.UpdateResultBO;

public interface EventPersistenceService extends PersistenceService{
	
	public EventBO retrieveEvent(int eventId) throws Exception;

	public UpdateResultBO cancelAttendance(int eventId, int attendanceId) throws Exception;

	public UpdateResultBO cancelEvent(int eventId)throws Exception;

	public CreateResultBO createEvent(EventBO bo) throws Exception;

	public CreateResultBO createEventMessage(int attendanceId, String message) throws Exception;

	public CreateResultBO createStandardEventMessage(int attendanceId, int stdMsgId) throws Exception;

	public CreateResultBO addFeedback(AttendanceBO bo, boolean isPositiveFeedback) throws Exception;

	public CreateResultBO createStandardEventMessage(AttendanceBO eo) throws Exception;

	public List<AttendanceBO> getAttendancesByEvent(int eventId) throws Exception;

	public List<EventBO> getEventsByUser(int userId) throws Exception;

	public List<EventBO> searchEvents(double gpsLat, double gpsLong, int idCategoria, int partecipanti) throws Exception;

	public List<MessageBO> getEventMessages(int eventId) throws Exception;

}
