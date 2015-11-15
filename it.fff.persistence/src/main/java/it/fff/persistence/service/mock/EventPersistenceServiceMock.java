package it.fff.persistence.service.mock;

import java.sql.SQLException;
import java.util.ArrayList;
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

public class EventPersistenceServiceMock implements EventPersistenceService{

	private static final Logger logger = LogManager.getLogger(EventPersistenceServiceMock.class);
	
	@Override
	public EventEO retrieveEvent(int eventId){
		logger.info("retrieveEvent ({})",eventId);
		EventEO event = new EventEO();
		event.setId(eventId);
		event.setNome("nome persistente");
		event.setDescrizione("descr persisente");
		logger.info("Mocked event ({}) retrieved",eventId);
		return event;
	}

	@Override
	public UpdateResultBO cancelAttendance(int eventId, int attendanceId) throws SQLException {
		UpdateResultBO resultBO = new UpdateResultBO();
		resultBO.setUpdatedKey(1);
		resultBO.setSuccess(true);
		resultBO.setNumRecordsUpdated(1);
		return resultBO;
	}

	@Override
	public UpdateResultBO cancelEvent(int eventId) throws SQLException {
		UpdateResultBO resultBO = new UpdateResultBO();
		resultBO.setUpdatedKey(1);
		resultBO.setSuccess(true);
		resultBO.setNumRecordsUpdated(1);
		return resultBO;
	}

	@Override
	public CreateResultBO createEvent(EventEO eo) throws SQLException {
		CreateResultBO resultBO = new CreateResultBO();
		resultBO.setCreatedKey(1);
		resultBO.setSuccess(true);
		resultBO.setNumRecordsCreated(1);
		return resultBO;
	}

	@Override
	public CreateResultBO createEventMessage(int attendanceId, String message) throws SQLException {
		CreateResultBO resultBO = new CreateResultBO();
		resultBO.setCreatedKey(1);
		resultBO.setSuccess(true);
		resultBO.setNumRecordsCreated(1);
		return resultBO;
	}

	@Override
	public CreateResultBO createStandardEventMessage(int attendanceId, int stdMsgId) throws SQLException {
		CreateResultBO resultBO = new CreateResultBO();
		resultBO.setCreatedKey(1);
		resultBO.setSuccess(true);
		resultBO.setNumRecordsCreated(1);
		return resultBO;
	}

	@Override
	public CreateResultBO addFeedback(AttendanceEO eo, boolean isPositiveFeedback) throws SQLException {
		CreateResultBO resultBO = new CreateResultBO();
		resultBO.setCreatedKey(1);
		resultBO.setSuccess(true);
		resultBO.setNumRecordsCreated(1);
		return resultBO;
	}

	@Override
	public CreateResultBO createStandardEventMessage(AttendanceEO eo) throws SQLException {
		CreateResultBO resultBO = new CreateResultBO();
		resultBO.setCreatedKey(1);
		resultBO.setSuccess(true);
		resultBO.setNumRecordsCreated(1);
		return resultBO;
	}

	@Override
	public List<AttendanceEO> getAttendancesByEvent(int eventId) throws SQLException {
		List<AttendanceEO> attendances = new ArrayList<AttendanceEO>();
		AttendanceEO a1 = new AttendanceEO();
		a1.setValid(true);
		a1.setNumPartecipanti(100);
		attendances.add(a1);
		return attendances;
	}

	@Override
	public List<EventEO> getEventsByUser(int userId) throws SQLException {
		List<EventEO> eos = new ArrayList<EventEO>();
		EventEO eo1 = new EventEO();
		eo1.setId(1);
		eo1.setNome("ev1");
		EventEO eo2 = new EventEO();
		eo2.setId(2);
		eo2.setNome("ev2");		
		eos.add(eo1);
		eos.add(eo2);
		
		return eos;
	}

	@Override
	public List<EventEO> searchEvents(double gpsLat, double gpsLong, int idCategoria, int partecipanti)	throws SQLException {
		List<EventEO> eos = new ArrayList<EventEO>();
		EventEO eo1 = new EventEO();
		eo1.setId(1);
		eo1.setNome("ev1");
		EventEO eo2 = new EventEO();
		eo2.setId(2);
		eo2.setNome("ev2");		
		eos.add(eo1);
		eos.add(eo2);
		
		return eos;
	}

	@Override
	public List<MessageEO> getEventMessages(int eventId) throws SQLException {
		List<MessageEO> eos = new ArrayList<MessageEO>();
		MessageEO eo1 = new MessageEO();
		eo1.setId(1);
		eo1.setText("aaaaaaaaaaaaa");
		MessageEO eo2 = new MessageEO();
		eo2.setId(2);
		eo2.setText("bbbbbbbbbbbbb");		
		eos.add(eo1);
		eos.add(eo2);
		return eos;
	}

}
