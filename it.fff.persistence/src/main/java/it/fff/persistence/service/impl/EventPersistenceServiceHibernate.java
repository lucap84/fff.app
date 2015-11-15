package it.fff.persistence.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.common.eo.AttendanceEO;
import it.fff.business.common.eo.EventEO;
import it.fff.business.common.eo.MessageEO;
import it.fff.persistence.service.EventPersistenceService;

public class EventPersistenceServiceHibernate implements EventPersistenceService{

	private static final Logger logger = LogManager.getLogger(EventPersistenceServiceHibernate.class);
	
	@Override
	public EventEO retrieveEvent(int eventId) throws SQLException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateResultBO cancelAttendance(int eventId, int attendanceId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateResultBO cancelEvent(int eventId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateResultBO createEvent(EventEO eo) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateResultBO createEventMessage(int attendanceId, String message) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateResultBO createStandardEventMessage(int attendanceId, int stdMsgId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateResultBO addFeedback(AttendanceEO eo, boolean isPositiveFeedback) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateResultBO createStandardEventMessage(AttendanceEO eo) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AttendanceEO> getAttendancesByEvent(int eventId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EventEO> getEventsByUser(int userId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EventEO> searchEvents(double gpsLat, double gpsLong, int idCategoria, int partecipanti)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageEO> getEventMessages(int eventId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
