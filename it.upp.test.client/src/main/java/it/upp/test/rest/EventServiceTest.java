package it.upp.test.rest;

import it.fff.clientserver.common.dto.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

public class EventServiceTest extends WebServiceRestTest{

	
	@Test
	public void getEventMessagesShouldReturnAtLeastOneMessaget(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String eventId = "1";
		
		WebTarget targetJSON = client.target(getBaseURI()).path("events").path(eventId).path("messages").path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).get();
		assertEquals(200, responseJSON.getStatus());
		final List<MessageDTO> entityFromJSON = responseJSON.readEntity(new GenericType<List<MessageDTO>>(){});
		assertNotNull(entityFromJSON);
		assertTrue(entityFromJSON.size()>0);
		
		WebTarget targetXML = client.target(getBaseURI()).path("events").path(eventId).path("messages").path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).get();
		assertEquals(200, responseXML.getStatus());
		List<MessageDTO> entityFromXML = responseXML.readEntity(new GenericType<List<MessageDTO>>(){});
		assertNotNull(entityFromXML);
		assertTrue(entityFromXML.size()>0);
	}	
	
	@Test
	public void postEventStandardMessageShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String eventId = "1";
		String attendanceId = "1";
		String sdtMsgId = "1";
		
		WebTarget targetJSON = client.target(getBaseURI()).path("events").path(eventId).path("attendances").path(attendanceId).path("messages").path("standard").path("json")
				.queryParam("sdtMsgId", sdtMsgId);
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).post(Entity.entity(null, MediaType.APPLICATION_JSON));
		assertEquals(200, responseJSON.getStatus());
		checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		
		WebTarget targetXML = client.target(getBaseURI()).path("events").path(eventId).path("attendances").path(attendanceId).path("messages").path("standard").path("xml")
				.queryParam("sdtMsgId", sdtMsgId);
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).post(Entity.entity(null, MediaType.APPLICATION_XML));
		assertEquals(200, responseXML.getStatus());
		checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);	
	}
	
	@Test
	public void postEventMessageShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String eventId = "1";
		String attendanceId = "1";
		String message = "Very long message posted to server Very long message posted to server Very long message posted to server Very long message posted to server...";
		
		WebTarget targetJSON = client.target(getBaseURI()).path("events").path(eventId).path("attendances").path(attendanceId).path("messages").path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).post(Entity.entity(message, MediaType.APPLICATION_JSON));
		assertEquals(200, responseJSON.getStatus());
		checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		
		WebTarget targetXML = client.target(getBaseURI()).path("events").path(eventId).path("attendances").path(attendanceId).path("messages").path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).post(Entity.entity(message, MediaType.APPLICATION_XML));
		assertEquals(200, responseXML.getStatus());
		checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);	
	}	
	
	@Test
	public void cancelAttendanceShouldReturnConfirm(){//annulla partecipazione da parte di uno partecipante
		Client client = WebServiceRestTest.getClientInstance();
		
		String eventId = "1";
		String attendanceToDel = "1";
		
		WebTarget targetJSON = client.target(getBaseURI()).path("events").path(eventId).path("attendances").path(attendanceToDel).path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).delete();
		checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		
		WebTarget targetXML = client.target(getBaseURI()).path("events").path(eventId).path("attendances").path(attendanceToDel).path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).delete();
		checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);
	}	
	
	@Test
	public void addFeedbackShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		EventDTO event = new EventDTO();
		event.setId("1");
		UserDTO attendee = new UserDTO();
		attendee.setId("1");
		FeedbackDTO feedback = new FeedbackDTO();
		feedback.setPositiveFeedback(true);
		AttendanceDTO attendanceToAddFeedback = new AttendanceDTO();
		attendanceToAddFeedback.setId("1");
		attendanceToAddFeedback.setEvent(event);
		attendanceToAddFeedback.setUser(attendee);
		attendanceToAddFeedback.setOrganizer(false);
		attendanceToAddFeedback.setNumPartecipanti(22);
		attendanceToAddFeedback.setFeedback(feedback);
		
		WebTarget targetJSON = client.target(getBaseURI()).path("events").path(event.getId()).path("attendances").path(attendanceToAddFeedback.getId()).path("feedback").path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).post(Entity.entity(attendanceToAddFeedback, MediaType.APPLICATION_JSON));
		assertEquals(200, responseJSON.getStatus());
		checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		
		WebTarget targetXML = client.target(getBaseURI()).path("events").path(event.getId()).path("attendances").path(attendanceToAddFeedback.getId()).path("feedback").path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).post(Entity.entity(attendanceToAddFeedback, MediaType.APPLICATION_XML));
		assertEquals(200, responseXML.getStatus());
		checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);	
	}	
	
	@Test
	public void joinEventShouldReturnAnAttendance(){
		Client client = WebServiceRestTest.getClientInstance();
		
		EventDTO event = new EventDTO();
		event.setId("1");
		UserDTO attendee = new UserDTO();
		attendee.setId("1");
		
		FeedbackDTO feedback = new FeedbackDTO();
		feedback.setPositiveFeedback(true);

		AttendanceDTO attendanceToCreate = new AttendanceDTO();
		attendanceToCreate.setId("1");
		attendanceToCreate.setEvent(event);
		attendanceToCreate.setUser(attendee);
		attendanceToCreate.setOrganizer(false);
		attendanceToCreate.setNumPartecipanti(22);
		attendanceToCreate.setFeedback(feedback);
		
		
		WebTarget targetJSON = client.target(getBaseURI()).path("events").path(event.getId()).path("attendances").path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).post(Entity.entity(attendanceToCreate, MediaType.APPLICATION_JSON));
		assertEquals(200, responseJSON.getStatus());
		checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		
		WebTarget targetXML = client.target(getBaseURI()).path("events").path(event.getId()).path("attendances").path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).post(Entity.entity(attendanceToCreate, MediaType.APPLICATION_XML));
		checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);
	}	
	
	@Test
	public void cancelEventShouldReturnConfirm(){ //annulla evento da parte dell'organizzatore
		Client client = WebServiceRestTest.getClientInstance();
		
		String eventToDelete = "1";
		
		WebTarget targetJSON = client.target(getBaseURI()).path("events").path(eventToDelete).path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).delete();
		checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		
		WebTarget targetXML = client.target(getBaseURI()).path("events").path(eventToDelete).path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).delete();
		checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);
	}	
	
	@Test
	public void createEventShouldReturnConfirm(){
		Client client = WebServiceRestTest.getClientInstance();
		
		UserDTO organizer = new UserDTO();
		organizer.setId("1");
		organizer.setNome("Nome organizer");
		organizer.setCognome("Cognome organizer");
		
		EventDTO dto = new EventDTO();
		dto.setNome("nuovo evento");
		dto.setUserOrganizer(organizer);
		
		WebTarget targetJSON = client.target(getBaseURI()).path("events").path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).post(Entity.entity(dto, MediaType.APPLICATION_JSON));
		checkEntityWriteResult(responseJSON,MediaType.APPLICATION_JSON);
		
		WebTarget targetXML = client.target(getBaseURI()).path("events").path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).post(Entity.entity(dto, MediaType.APPLICATION_XML));
		checkEntityWriteResult(responseXML,MediaType.APPLICATION_XML);	
	}	
	
	@Test
	public void getAttendacesByEventShouldReturnAtLeastOneAttendance(){
		Client client = WebServiceRestTest.getClientInstance();

		String requestedEventId = "1";
		
		WebTarget targetJSON = client.target(getBaseURI()).path("events").path(requestedEventId).path("attendaces").path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).get();
		assertEquals(200, responseJSON.getStatus());
		List<AttendanceDTO> entityFromJSON = responseJSON.readEntity(new GenericType<List<AttendanceDTO>>(){});
		assertNotNull(entityFromJSON);
		assertTrue(entityFromJSON.size()>0);
		
		WebTarget targetXML = client.target(getBaseURI()).path("events").path(requestedEventId).path("attendaces").path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).get();
		assertEquals(200, responseXML.getStatus());
		List<AttendanceDTO> entityFromXML = responseXML.readEntity(new GenericType<List<AttendanceDTO>>(){});
		assertNotNull(entityFromXML);
		assertTrue(entityFromXML.size()>0);
	}
	
	@Test
	public void getEventShouldReturnOneEvent(){
		Client client = WebServiceRestTest.getClientInstance();

		String requestedEventId = "1";
		
		WebTarget targetJSON = client.target(getBaseURI()).path("events").path(requestedEventId).path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).get();
		assertEquals(200, responseJSON.getStatus());
//		final ReadResultDTO<EventDTO> entityFromJSON = responseJSON.readEntity(new GenericType<ReadResultDTO<EventDTO>>(){});
		final EventDTO entityFromJSON = responseJSON.readEntity(EventDTO.class);
		assertNotNull(entityFromJSON);
		assertEquals(entityFromJSON.getId(), requestedEventId);
		
		WebTarget targetXML = client.target(getBaseURI()).path("events").path(requestedEventId).path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).get();
		assertEquals(200, responseXML.getStatus());
//		final ReadResultDTO<EventDTO> entityFromXML = responseXML.readEntity(new GenericType<ReadResultDTO<EventDTO>>(){});
		final EventDTO entityFromXML = responseXML.readEntity(EventDTO.class);
		assertNotNull(entityFromXML);
		assertEquals(entityFromXML.getId(), requestedEventId);
	}
	
	@Test
	public void searchEventsShouldReturnAtLeastOneEvent(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String gpsLat = "1.1234";
		String gpsLong = "2.4567";
		String idCategoria = "1";
		String partecipanti = "3";
		
		WebTarget targetJSON = client.target(getBaseURI()).path("events").path("json").
				queryParam("gpsLat", gpsLat).
				queryParam("gpsLong", gpsLong).
				queryParam("idCategoria", idCategoria).
				queryParam("partecipanti", partecipanti);
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).get();
		assertEquals(200, responseJSON.getStatus());
		final List<EventDTO> entityFromJSON = responseJSON.readEntity(new GenericType<List<EventDTO>>(){});
		assertNotNull(entityFromJSON);
		assertTrue(entityFromJSON.size()>0);
		
		WebTarget targetXML = client.target(getBaseURI()).path("events").path("xml").
				queryParam("gpsLat", gpsLat).
				queryParam("gpsLong", gpsLong).
				queryParam("idCategoria", idCategoria).
				queryParam("partecipanti", partecipanti);
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).get();
		assertEquals(200, responseXML.getStatus());
		List<EventDTO> entityFromXML = responseXML.readEntity(new GenericType<List<EventDTO>>(){});
		assertNotNull(entityFromXML);
		assertTrue(entityFromXML.size()>0);
	}	
	
	public static void main(String[] args) {
		EventServiceTest eventServiceTest = new EventServiceTest();
//		eventServiceTest.joinEventShouldReturnAnAttendance();
		eventServiceTest.postEventStandardMessageShouldReturnConfirm();
	}
	
}
