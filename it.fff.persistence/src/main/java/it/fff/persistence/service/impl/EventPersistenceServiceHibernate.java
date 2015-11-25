package it.fff.persistence.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.MessageBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.common.eo.AttendanceEO;
import it.fff.business.common.eo.EventEO;
import it.fff.business.common.eo.MessageEO;
import it.fff.persistence.service.EventPersistenceService;

public class EventPersistenceServiceHibernate implements EventPersistenceService{

	private static final Logger logger = LogManager.getLogger(EventPersistenceServiceHibernate.class);
	
	@Override
	public EventBO retrieveEvent(int eventId) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateResultBO cancelAttendance(int eventId, int attendanceId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateResultBO cancelEvent(int eventId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateResultBO createEvent(EventBO bo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateResultBO createEventMessage(int attendanceId, String message) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateResultBO createStandardEventMessage(int attendanceId, int stdMsgId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateResultBO addFeedback(AttendanceBO bo, boolean isPositiveFeedback) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateResultBO createStandardEventMessage(AttendanceBO bo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AttendanceBO> getAttendancesByEvent(int eventId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EventBO> getEventsByUser(int userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EventBO> searchEvents(double gpsLat, double gpsLong, int idCategoria, int partecipanti)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageBO> getEventMessages(int eventId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
