package it.fff.persistence.service.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.EventCategoryBO;
import it.fff.business.common.bo.MessageBO;
import it.fff.business.common.bo.MessageStandardBO;
import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.clientserver.common.enums.AttendanceStateEnum;
import it.fff.clientserver.common.enums.EventStateEnum;
import it.fff.clientserver.common.enums.FeedbackEnum;
import it.fff.clientserver.common.util.Constants;
import it.fff.clientserver.common.util.FlokkerUtils;
import it.fff.persistence.exception.PersistenceException;
import it.fff.persistence.service.EventPersistenceService;

public class EventPersistenceServiceMock implements EventPersistenceService{

	private static final Logger logger = LogManager.getLogger(EventPersistenceServiceMock.class);
	
	@Override
	public EventBO retrieveEvent(int eventId) throws PersistenceException {
		TypologicalPersistenceServiceMock typologicalMock = new TypologicalPersistenceServiceMock();

		EventBO bo1 = new EventBO();
		bo1.setId(eventId);
		bo1.setTitolo("Evento mock "+eventId);
		bo1.setDescrizione("descrizione evento prova "+eventId);
		bo1.setDataInizio(Constants.DATE_FORMATTER.format(new Date()));
		bo1.setDurata(FlokkerUtils.generateRandomIntBetween(1,10));
		
		EventCategoryBO catBo1 = typologicalMock.getAllEventCategories().get(FlokkerUtils.generateRandomIntBetween(0,typologicalMock.getAllEventCategories().size()-1));
		bo1.setCategoria(catBo1);
		
		PlaceBO placeBo1 = new PlacesPersistenceServiceMock().getPlaceByGPS(1.234,2.345);
		bo1.setLocation(placeBo1);
		bo1.setStato(typologicalMock.getAllEventStates().get(FlokkerUtils.generateRandomIntBetween(0,typologicalMock.getAllEventStates().size()-1)));
		
		bo1.setMessages(this.getEventMessages(eventId));
		bo1.setPartecipazioni(this.getAttendancesByEvent(eventId));
		
		return bo1;
	}

	@Override
	public WriteResultBO cancelEvent(int eventId, int organizerId) throws PersistenceException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(eventId);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public WriteResultBO createEvent(EventBO bo) throws PersistenceException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public WriteResultBO createEventMessage(int attendanceId, String message) throws PersistenceException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(attendanceId);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public WriteResultBO createStandardEventMessage(int attendanceId, int stdMsgId) throws PersistenceException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(stdMsgId);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public WriteResultBO addFeedback(int attendanceId, FeedbackEnum feedback) throws PersistenceException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(attendanceId);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public WriteResultBO createAttandance(AttendanceBO bo) throws PersistenceException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public List<AttendanceBO> getAttendancesByEvent(int eventId) throws PersistenceException {
		List<AttendanceBO> attendances = new ArrayList<AttendanceBO>();
		UserBO organizerUserBO1 = new UserPersistenceServiceMock().getUser(1);
		UserBO organizerUserBO2 = new UserPersistenceServiceMock().getUser(2);

		AttendanceBO attendanceBO1 = new AttendanceBO();
		attendanceBO1.setId(1);
		attendanceBO1.setDataAggiornamento(Constants.DATE_FORMATTER.format(new Date()));
		attendanceBO1.setDataCreazione(Constants.DATE_FORMATTER.format(new Date()));
		attendanceBO1.setFeedback(FeedbackEnum.POSITIVE);
		attendanceBO1.setStato(AttendanceStateEnum.OUTPLACE);
		attendanceBO1.setValid(true);
		attendanceBO1.setNumeroOspiti(2);
		attendanceBO1.setOrganizer(true);
		attendanceBO1.setUtente(organizerUserBO1);
		attendanceBO1.setEventId(eventId);
		
		AttendanceBO attendanceBO2 = new AttendanceBO();
		attendanceBO2.setId(2);
		attendanceBO2.setDataAggiornamento(Constants.DATE_FORMATTER.format(new Date()));
		attendanceBO2.setDataCreazione(Constants.DATE_FORMATTER.format(new Date()));
		attendanceBO2.setFeedback(FeedbackEnum.NEGATIVE);
		attendanceBO2.setStato(AttendanceStateEnum.INPLACE);
		attendanceBO2.setValid(true);
		attendanceBO2.setNumeroOspiti(4);
		attendanceBO2.setOrganizer(false);
		attendanceBO2.setUtente(organizerUserBO2);
		attendanceBO2.setEventId(eventId);
		
		
		attendances.add(attendanceBO1);
		attendances.add(attendanceBO2);
		return attendances;
	}

	@Override
	public List<EventBO> getEventsByUser(int userId) throws PersistenceException {
		List<EventBO> bos = new ArrayList<EventBO>();
		
		EventBO bo1 = this.retrieveEvent(1);
		EventBO bo2 = this.retrieveEvent(2);
		EventBO bo3 = this.retrieveEvent(3);
		
		bos.add(bo1);
		bos.add(bo2);
		bos.add(bo3);
		
		return bos;
	}

	@Override
	public List<EventBO> searchEvents(	double gpsLatFrom, 
										double gpsLatTo, 
										double gpsLongFrom,
										double gpsLongTo, 
										int idCategoria, 
										int minPartecipanti)	throws PersistenceException {

		List<EventBO> bos = this.getEventsByUser(1);
		
		return bos;
	}

	@Override
	public List<MessageBO> getEventMessages(int eventId) throws PersistenceException {
		EventBO event = new EventBO();
		event.setId(1);
		
		AttendanceBO att = new AttendanceBO();
		att.setId(1);
		att.setEventId(eventId);
		
		List<MessageBO> messagesBO = new ArrayList<MessageBO>();
		MessageBO messageBO1 = new MessageBO();
		messageBO1.setId(1);
		messageBO1.setText("Testo messaggio mock 1");
		messageBO1.setDataCreazione(Constants.DATE_FORMATTER.format(new Date()));
		messageBO1.setMsgStd(null);
		messageBO1.setAttendanceId(att.getId());
		messageBO1.setEventId(event.getId());
		
		MessageBO messageBO2 = new MessageBO();
		messageBO2.setId(2);
		messageBO2.setText("Testo messaggio mock 2");
		messageBO2.setDataCreazione(Constants.DATE_FORMATTER.format(new Date()));
		
		MessageStandardBO msgstdbo1 = new MessageStandardBO();
		msgstdbo1.setId(1);
		msgstdbo1.setText("Non vi vedo, dove state?");
		
		messageBO2.setMsgStd(msgstdbo1);
		messageBO2.setAttendanceId(att.getId());
		messageBO2.setEventId(event.getId());
		
		
		messagesBO.add(messageBO1);
		messagesBO.add(messageBO2);
		
		return messagesBO;
	}

	@Override
	public WriteResultBO updateEventState(int eventId, EventStateEnum state) throws PersistenceException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

}
