package it.fff.persistence.service;

import java.util.List;

import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.common.eo.AttendanceEO;
import it.fff.business.common.eo.EventEO;
import it.fff.business.common.eo.MessageEO;

public interface EventPersistenceService extends PersistenceService{
	
	public EventEO retrieveEvent(int eventId) throws Exception;

	public UpdateResultBO cancelAttendance(int eventId, int attendanceId) throws Exception;

	public UpdateResultBO cancelEvent(int eventId)throws Exception;

	public CreateResultBO createEvent(EventEO eo) throws Exception;

	public CreateResultBO createEventMessage(int attendanceId, String message) throws Exception;

	public CreateResultBO createStandardEventMessage(int attendanceId, int stdMsgId) throws Exception;

	public CreateResultBO addFeedback(AttendanceEO eo, boolean isPositiveFeedback) throws Exception;

	public CreateResultBO createStandardEventMessage(AttendanceEO eo) throws Exception;

	public List<AttendanceEO> getAttendancesByEvent(int eventId) throws Exception;

	public List<EventEO> getEventsByUser(int userId) throws Exception;

	public List<EventEO> searchEvents(double gpsLat, double gpsLong, int idCategoria, int partecipanti) throws Exception;

	public List<MessageEO> getEventMessages(int eventId) throws Exception;

}
