package it.fff.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.business.service.EventBusinessService;
import it.fff.persistence.facade.exception.PersistenceException;
import it.fff.persistence.facade.service.PersistenceServiceFacade;

public class EventBusinessServiceImpl implements EventBusinessService{
	
	private static final Logger logger = LogManager.getLogger(EventBusinessServiceImpl.class);
	
	private PersistenceServiceFacade persistenceFacade;

	public PersistenceServiceFacade getPersistenceFacade() {
		return persistenceFacade;
	}

	public void setPersistenceFacade(PersistenceServiceFacade persistenceFacade) {
		this.persistenceFacade = persistenceFacade;
	}	
	
	@Override
	public List<AttendanceBO> getAttendancesByEvent(int eventId) {
		
//		List<AttendanceBO> attendances = persistenceFacade.getAttendancesByEvent(eventId);
		
		ArrayList<AttendanceBO> attendances = new ArrayList<AttendanceBO>();
		AttendanceBO a1 = new AttendanceBO();
		a1.setValid(true);
		a1.setNumPartecipanti(100);
		attendances.add(a1);
		return attendances;
	}	
	
	@Override
	public EventBO getEvent(int eventId) throws PersistenceException{
		logger.info("EventBusinessServiceImpl retrieving event...");
		EventBO event = persistenceFacade.retrieveEvent(eventId);
		if(event!=null){
			logger.info("EventBusinessServiceImpl retrieved");
		}
		return event;
	}
	
	@Override
	public CreateResultBO createEvent(EventBO bo) throws PersistenceException {
		CreateResultBO createResultBO = new CreateResultBO();
		createResultBO.setCreatedKey(1);
		createResultBO.setSuccess(true);
		createResultBO.setNumRecordsCreated(1);
		return createResultBO;
	}


	@Override
	public UpdateResultBO cancelEvent(int eventId) throws PersistenceException {
		UpdateResultBO createResultBO = new UpdateResultBO();
		createResultBO.setUpdatedKey(1);
		createResultBO.setSuccess(true);
		createResultBO.setNumRecordsUpdated(1);
		return createResultBO;
	}	

	@Override
	public UpdateResultBO cancelAttendance(int eventIdInt, int attendanceIdInt) throws PersistenceException {
		UpdateResultBO createResultBO = new UpdateResultBO();
		createResultBO.setUpdatedKey(1);
		createResultBO.setSuccess(true);
		createResultBO.setNumRecordsUpdated(1);
		return createResultBO;
	}

	@Override
	public CreateResultBO postEventMessage(int attendanceId, String message) throws PersistenceException {
		CreateResultBO createResultBO = new CreateResultBO();
		createResultBO.setCreatedKey(1);
		createResultBO.setSuccess(true);
		createResultBO.setNumRecordsCreated(1);
		return createResultBO;
	}

	@Override
	public CreateResultBO postStandardEventMessage(int attendanceId, int stdMsgId) throws PersistenceException {
		CreateResultBO createResultBO = new CreateResultBO();
		createResultBO.setCreatedKey(1);
		createResultBO.setSuccess(true);
		createResultBO.setNumRecordsCreated(1);
		return createResultBO;
	}
	
	

}
