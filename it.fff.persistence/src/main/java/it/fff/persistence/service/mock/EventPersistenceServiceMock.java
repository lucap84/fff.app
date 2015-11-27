package it.fff.persistence.service.mock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NameAlreadyBoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.fff.business.common.bo.AttendanceBO;
import it.fff.business.common.bo.CityBO;
import it.fff.business.common.bo.CreateResultBO;
import it.fff.business.common.bo.EventBO;
import it.fff.business.common.bo.EventCategoryBO;
import it.fff.business.common.bo.EventStateBO;
import it.fff.business.common.bo.MessageBO;
import it.fff.business.common.bo.NazioneBO;
import it.fff.business.common.bo.PlaceBO;
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
		event.setTitolo("nome persistente");
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
		a1.setUtente(user);
		
		attendances.add(a1);
		return attendances;
	}

	@Override
	public List<EventBO> getEventsByUser(int userId) throws SQLException {
		List<EventBO> bos = new ArrayList<EventBO>();
		EventBO bo1 = new EventBO();
		bo1.setId(1);
		bo1.setTitolo("ev1");
		bo1.setDescrizione("descr");
		bo1.setDataInizio("2015-11-27_10-10-10");
		bo1.setDurata(4);
		
		EventCategoryBO catBo1 = new EventCategoryBO();
		catBo1.setId(1);
		catBo1.setNome("cat");
		catBo1.setDescrizione("descr cat1");
		bo1.setCategoria(catBo1);
		
		NazioneBO nazioneBo1 = new NazioneBO();
		nazioneBo1.setId(1);
		nazioneBo1.setInternationalKey("IT");
		nazioneBo1.setNome("Italia");
		
		CityBO cityBo1 = new CityBO();
		cityBo1.setId(1);
		cityBo1.setNome("Roma");
		cityBo1.setNazione(nazioneBo1);
		
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
		
		EventStateBO statoBo1 = new EventStateBO();
		statoBo1.setId(1);
		statoBo1.setNome("Chiuso");
		statoBo1.setDescrizione("L'evento è terminato");
		bo1.setStato(statoBo1);
	
		EventBO bo2 = new EventBO();
		bo2.setId(2);
		bo2.setTitolo("ev2");
		bo2.setDescrizione("descr");
		bo2.setDataInizio("2015-11-27_11-11-11");
		bo2.setDurata(6);
		
		EventCategoryBO catbo2 = new EventCategoryBO();
		catbo2.setId(2);
		catbo2.setNome("cat2");
		catbo2.setDescrizione("descr cat2");
		bo2.setCategoria(catbo2);
		
		NazioneBO nazionebo2 = new NazioneBO();
		nazionebo2.setId(2);
		nazionebo2.setInternationalKey("JP");
		nazionebo2.setNome("Giappone");
		
		CityBO citybo2 = new CityBO();
		citybo2.setId(2);
		citybo2.setNome("Tokyo");
		citybo2.setNazione(nazionebo2);
		
		PlaceBO placebo2 = new PlaceBO();
		placebo2.setId(2);
		placebo2.setNome("place2");
		placebo2.setGpsLat(1.444);
		placebo2.setGpsLong(2.555);
		placebo2.setCity(citybo2);
		
		bo2.setLocation(placebo2);
		
		EventStateBO statobo2 = new EventStateBO();
		statobo2.setId(2);
		statobo2.setNome("Aperto");
		statobo2.setDescrizione("L'evento è aperto");
		bo2.setStato(statobo2);		

		
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
		bo1.setTitolo("ev1");
		EventBO bo2 = new EventBO();
		bo2.setId(2);
		bo2.setTitolo("ev2");		
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
