package it.fff.persistence.service;

import java.sql.SQLException;
import java.util.List;

import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.common.eo.AttendanceEO;
import it.fff.business.common.eo.EventEO;
import it.fff.business.common.eo.MessageEO;

public interface EventPersistenceService extends PersistenceService{
	
	public EventEO retrieveEvent(int eventId) throws SQLException;

	public UpdateResultBO cancelAttendance(int eventId, int attendanceId) throws SQLException;

	public UpdateResultBO cancelEvent(int eventId)throws SQLException;

	public CreateResultBO createEvent(EventEO eo) throws SQLException;

	public CreateResultBO createEventMessage(int attendanceId, String message) throws SQLException;

	public CreateResultBO createStandardEventMessage(int attendanceId, int stdMsgId) throws SQLException;

	public CreateResultBO addFeedback(AttendanceEO eo, boolean isPositiveFeedback) throws SQLException;

	public CreateResultBO createStandardEventMessage(AttendanceEO eo) throws SQLException;

	public List<AttendanceEO> getAttendancesByEvent(int eventId) throws SQLException;

	public List<EventEO> getEventsByUser(int userId) throws SQLException;

	public List<EventEO> searchEvents(double gpsLat, double gpsLong, int idCategoria, int partecipanti) throws SQLException;

	public List<MessageEO> getEventMessages(int eventId) throws SQLException;

}
