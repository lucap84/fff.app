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
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.eo.AttendanceEO;
import it.fff.business.common.eo.EventEO;
import it.fff.business.common.eo.MessageEO;
import it.fff.business.common.eo.UserEO;
import it.fff.persistence.service.EventPersistenceService;

public class EventPersistenceServiceMock implements EventPersistenceService{

	private static final Logger logger = LogManager.getLogger(EventPersistenceServiceMock.class);
	
	@Override
	public EventBO retrieveEvent(int eventId){
		logger.info("retrieveEvent ({})",eventId);
		EventBO event = new EventBO();
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
	public CreateResultBO createEvent(EventBO bo) throws SQLException {
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
	public CreateResultBO addFeedback(AttendanceBO bo, boolean isPositiveFeedback) throws SQLException {
		CreateResultBO resultBO = new CreateResultBO();
		resultBO.setCreatedKey(1);
		resultBO.setSuccess(true);
		resultBO.setNumRecordsCreated(1);
		return resultBO;
	}

	@Override
	public CreateResultBO createStandardEventMessage(AttendanceBO bo) throws SQLException {
		CreateResultBO resultBO = new CreateResultBO();
		resultBO.setCreatedKey(1);
		resultBO.setSuccess(true);
		resultBO.setNumRecordsCreated(1);
		return resultBO;
	}

	@Override
	public List<AttendanceBO> getAttendancesByEvent(int eventId) throws SQLException {
		List<AttendanceBO> attendances = new ArrayList<AttendanceBO>();
		AttendanceBO a1 = new AttendanceBO();
		a1.setValid(true);
		a1.setNumPartecipanti(100);
		
		UserBO user = new UserBO();
		user.setId(1);
		a1.setUser(user);
		
		attendances.add(a1);
		return attendances;
	}

	@Override
	public List<EventBO> getEventsByUser(int userId) throws SQLException {
		List<EventBO> bos = new ArrayList<EventBO>();
		EventBO bo1 = new EventBO();
		bo1.setId(1);
		bo1.setNome("ev1");
		EventBO bo2 = new EventBO();
		bo2.setId(2);
		bo2.setNome("ev2");		
		bos.add(bo1);
		bos.add(bo2);
		
		return bos;
	}

	@Override
	public List<EventBO> searchEvents(double gpsLatFrom, 
			double gpsLatTo, 
			double gpsLongFrom,
			double gpsLongTo, 
			int idCategoria, 
			int minPartecipanti)	throws Exception {

		List<EventBO> bos = new ArrayList<EventBO>();
		EventBO bo1 = new EventBO();
		bo1.setId(1);
		bo1.setNome("ev1");
		EventBO bo2 = new EventBO();
		bo2.setId(2);
		bo2.setNome("ev2");		
		bos.add(bo1);
		bos.add(bo2);
		
		return bos;
	}

	@Override
	public List<MessageBO> getEventMessages(int eventId) throws SQLException {
		List<MessageBO> bos = new ArrayList<MessageBO>();
		MessageBO bo1 = new MessageBO();
		bo1.setId(1);
		bo1.setText("aaaaaaaaaaaaa");
		MessageBO bo2 = new MessageBO();
		bo2.setId(2);
		bo2.setText("bbbbbbbbbbbbb");		
		bos.add(bo1);
		bos.add(bo2);
		return bos;
	}

}
