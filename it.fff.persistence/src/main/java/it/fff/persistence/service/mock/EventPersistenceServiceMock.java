package it.fff.persistence.service.mock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.CityBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.EventCategoryBO;
import it.fff.business.common.bo.MessageBO;
import it.fff.business.common.bo.MessageStandardBO;
import it.fff.business.common.bo.NationBO;
import it.fff.business.common.bo.PlaceBO;
import it.fff.business.common.bo.UserBO;
import it.fff.business.common.bo.WriteResultBO;
import it.fff.clientserver.common.enums.EventStateEnum;
import it.fff.clientserver.common.enums.FeedbackEnum;
import it.fff.persistence.service.EventPersistenceService;

public class EventPersistenceServiceMock implements EventPersistenceService{

	private static final Logger logger = LogManager.getLogger(EventPersistenceServiceMock.class);
	
	@Override
	public EventBO retrieveEvent(int eventId){
		EventBO bo1 = new EventBO();
		bo1.setId(eventId);
		bo1.setTitolo("Evento mock");
		bo1.setDescrizione("descrizione evento prova");
		bo1.setDataInizio("2015-11-27_10-10-10");
		bo1.setDurata(4);
		
		EventCategoryBO catBo1 = new EventCategoryBO();
		catBo1.setId(3);
		catBo1.setNome("Arte e cultura");
		catBo1.setDescrizione("Arte e cultura");
		
		bo1.setCategoria(catBo1);
		
		NationBO nazioneBO1 = new NationBO();
		nazioneBO1.setId(1);
		nazioneBO1.setNome("Italia");
		nazioneBO1.setInternationalKey("380");
		nazioneBO1.setInternationalCode("ITA");
		
		CityBO cityBo1 = new CityBO();
		cityBo1.setId(1);
		cityBo1.setNome("Roma");
		cityBo1.setNazione(nazioneBO1);
		
		PlaceBO placeBo1 = new PlaceBO();
		placeBo1.setId(1);
		placeBo1.setNome("Luogo mock");
		placeBo1.setGpsLat(1.234);
		placeBo1.setGpsLong(2.345);
		placeBo1.setVia("via 1");
		placeBo1.setCivico("civico 1");
		placeBo1.setCap("Cap 00100");
		placeBo1.setCity(cityBo1);
		
		bo1.setLocation(placeBo1);
		
//		EventStateBO statoEventoBo1 = new EventStateBO();
//		statoEventoBo1.setId(1);
//		statoEventoBo1.setNome("Attivo");
//		statoEventoBo1.setDescrizione("EventState Attivo");
		
		bo1.setStato(EventStateEnum.ACTIVE);
		
		bo1.setMessages(new ArrayList<MessageBO>());
		bo1.setPartecipazioni(new ArrayList<AttendanceBO>());
		
		return bo1;
	}

	@Override
	public WriteResultBO cancelEvent(int eventId, int organizerId) throws SQLException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(eventId);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public WriteResultBO createEvent(EventBO bo) throws SQLException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public WriteResultBO createEventMessage(int attendanceId, String message) throws SQLException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(attendanceId);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public WriteResultBO createStandardEventMessage(int attendanceId, int stdMsgId) throws SQLException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(stdMsgId);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public WriteResultBO addFeedback(int attendanceId, FeedbackEnum feedback) throws SQLException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(attendanceId);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public WriteResultBO createAttandance(AttendanceBO bo) throws SQLException {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

	@Override
	public List<AttendanceBO> getAttendancesByEvent(int eventId) throws SQLException {
		List<AttendanceBO> attendances = new ArrayList<AttendanceBO>();

		AttendanceBO attendanceBO1 = new AttendanceBO();
		attendanceBO1.setId(1);
		attendanceBO1.setValid(true);
		attendanceBO1.setNumPartecipanti(5);
		attendanceBO1.setOrganizer(true);
		
		UserBO organizerUserBO1 = new UserPersistenceServiceMock().getUser(1);
		attendanceBO1.setUtente(organizerUserBO1);
		attendanceBO1.setEvent(this.retrieveEvent(1));
		
		AttendanceBO attendanceBO2 = new AttendanceBO();
		attendanceBO2.setId(2);
		attendanceBO2.setValid(true);
		attendanceBO2.setNumPartecipanti(12);
		attendanceBO2.setOrganizer(false);
		
		UserBO organizerUserBO2 = new UserPersistenceServiceMock().getUser(2);
		attendanceBO2.setUtente(organizerUserBO2);
		attendanceBO2.setEvent(this.retrieveEvent(2));
		
		
		attendances.add(attendanceBO1);
		attendances.add(attendanceBO2);
		return attendances;
	}

	@Override
	public List<EventBO> getEventsByUser(int userId) throws SQLException {
		List<EventBO> bos = new ArrayList<EventBO>();
		EventBO bo1 = new EventBO();
		bo1.setId(1);
		bo1.setTitolo("Evento mock1");
		bo1.setDescrizione("descr");
		bo1.setDataInizio("2015-11-27_10-10-10");
		bo1.setDurata(4);
		
		EventCategoryBO catBo1 = new EventCategoryBO();
		catBo1.setId(1);
		catBo1.setNome("cat");
		catBo1.setDescrizione("descr cat1");
		bo1.setCategoria(catBo1);
		
		NationBO nazioneBO1 = new NationBO();
		nazioneBO1.setId(1);
		nazioneBO1.setNome("Italia");
		nazioneBO1.setInternationalKey("380");
		nazioneBO1.setInternationalCode("ITA");
		
		CityBO cityBo1 = new CityBO();
		cityBo1.setId(1);
		cityBo1.setNome("Roma");
		cityBo1.setNazione(nazioneBO1);
		
		PlaceBO placeBo1 = new PlaceBO();
		placeBo1.setId(1);
		placeBo1.setNome("place");
		placeBo1.setGpsLat(1.234);
		placeBo1.setGpsLong(2.345);
		placeBo1.setVia("via 1");
		placeBo1.setCivico("civico 1");
		placeBo1.setCap("Cap 00100");
		placeBo1.setCity(cityBo1);
		
		bo1.setLocation(placeBo1);
		
//		EventStateBO statoBo1 = new EventStateBO();
//		statoBo1.setId(1);
//		statoBo1.setNome("Chiuso");
//		statoBo1.setDescrizione("L'evento è terminato");
		bo1.setStato(EventStateEnum.FINISHED);
		
		List<AttendanceBO> attendancesByEvent1 = this.getAttendancesByEvent(bo1.getId());
		bo1.setPartecipazioni(attendancesByEvent1);
	
		EventBO bo2 = new EventBO();
		bo2.setId(2);
		bo2.setTitolo("Evento mock2");
		bo2.setDescrizione("descr");
		bo2.setDataInizio("2015-11-27_11-11-11");
		bo2.setDurata(6);
		
		EventCategoryBO catbo2 = new EventCategoryBO();
		catbo2.setId(2);
		catbo2.setNome("cat2");
		catbo2.setDescrizione("descr cat2");
		bo2.setCategoria(catbo2);
		
		NationBO nationBO2 = new NationBO();
		nationBO2.setId(3);
		nationBO2.setNome("Francia");
		nationBO2.setInternationalKey("250");
		nationBO2.setInternationalCode("FRA");
		
		CityBO citybo2 = new CityBO();
		citybo2.setId(2);
		citybo2.setNome("Parigi");
		citybo2.setNazione(nationBO2);
		
		PlaceBO placebo2 = new PlaceBO();
		placebo2.setId(2);
		placebo2.setNome("place2");
		placebo2.setGpsLat(1.444);
		placebo2.setGpsLong(2.555);
		placebo2.setCity(citybo2);
		
		bo2.setLocation(placebo2);
		
//		EventStateBO statobo2 = new EventStateBO();
//		statobo2.setId(2);
//		statobo2.setNome("Aperto");
//		statobo2.setDescrizione("L'evento è aperto");
		bo2.setStato(EventStateEnum.ACTIVE);		

		List<AttendanceBO> attendancesByEvent2 = this.getAttendancesByEvent(bo2.getId());
		bo2.setPartecipazioni(attendancesByEvent2);
		
		bos.add(bo1);
		bos.add(bo2);
		
		return bos;
	}

	@Override
	public List<EventBO> searchEvents(	double gpsLatFrom, 
										double gpsLatTo, 
										double gpsLongFrom,
										double gpsLongTo, 
										int idCategoria, 
										int minPartecipanti)	throws Exception {

		List<EventBO> bos = this.getEventsByUser(1);
		
		return bos;
	}

	@Override
	public List<MessageBO> getEventMessages(int eventId) throws SQLException {
		EventBO event = new EventBO();
		event.setId(1);
		
		AttendanceBO att = new AttendanceBO();
		att.setId(1);
		att.setEvent(event);
		
		List<MessageBO> messagesBO = new ArrayList<MessageBO>();
		MessageBO messageBO1 = new MessageBO();
		messageBO1.setId(1);
		messageBO1.setText("Testo messaggio mock 1");
		messageBO1.setDataCreazione("1998-01-01");
		messageBO1.setMsgStd(null);
		messageBO1.setAttendanceId(att.getId());
		messageBO1.setEventId(event.getId());
		
		MessageBO messageBO2 = new MessageBO();
		messageBO2.setId(2);
		messageBO2.setText("");
		messageBO2.setDataCreazione("1999-01-01");
		
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
	public WriteResultBO updateEventState(int eventId, EventStateEnum state) throws Exception {
		WriteResultBO resultBO = new WriteResultBO();
		resultBO.setWrittenKey(1);
		resultBO.setSuccess(true);
		resultBO.setAffectedRecords(1);
		return resultBO;
	}

}
