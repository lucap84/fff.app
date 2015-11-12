package it.fff.business.service;

import java.util.List;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.UpdateResultBO;
import it.fff.persistence.facade.exception.PersistenceException;

public interface EventBusinessService extends BusinessService{
	
	public EventBO getEvent(int eventId) throws  PersistenceException;
	
	public List<AttendanceBO> getAttendancesByEvent(int eventId) throws  PersistenceException;

	public CreateResultBO createEvent(EventBO bo) throws  PersistenceException;

	public UpdateResultBO cancelEvent(int eventId) throws  PersistenceException;

	public UpdateResultBO cancelAttendance(int eventId, int attendanceIdInt)  throws  PersistenceException;

	public CreateResultBO postEventMessage(int attendanceId, String message)  throws  PersistenceException;

	public CreateResultBO postStandardEventMessage(int attendanceId, int stdMsgId) throws  PersistenceException;	

}
