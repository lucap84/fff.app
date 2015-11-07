package it.upp.test.rest;

import it.fff.business.common.dto.*;
import it.fff.business.service.provider.AttendanceDTOMessageBodyRW;
import it.fff.business.service.provider.EventDTOMessageBodyRW;
import it.fff.business.service.provider.WriteResultDTOMessageBodyRW;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

public class EventServiceTest extends ServiceTest{

	
	@Test
	public void addFeedbackShouldReturnConfirm(){
		Client client = ClientBuilder.newBuilder().build();
		client.register(AttendanceDTOMessageBodyRW.class);
		client.register(WriteResultDTOMessageBodyRW.class);
		
		EventDTO event = new EventDTO();
		event.setEventId("1");
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
		
		WebTarget targetJSON = client.target(getBaseURI()).path("events").path(event.getEventId()).path("attendances").path(attendanceToAddFeedback.getId()).path("feedbacks").path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).post(Entity.entity(attendanceToAddFeedback, MediaType.APPLICATION_JSON));
		assertEquals(200, responseJSON.getStatus());
		checkEntityWriteResultJSON(responseJSON);
		
		WebTarget targetXML = client.target(getBaseURI()).path("events").path(event.getEventId()).path("attendances").path(attendanceToAddFeedback.getId()).path("feedbacks").path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).post(Entity.entity(attendanceToAddFeedback, MediaType.APPLICATION_XML));
		assertEquals(200, responseXML.getStatus());
		checkEntityWriteResultXML(responseXML);	
	}	
	
	@Test
	public void joinEventShouldReturnAnAttendance(){
		Client client = ClientBuilder.newBuilder().build();
		client.register(AttendanceDTOMessageBodyRW.class);
		
		EventDTO event = new EventDTO();
		event.setEventId("1");
		UserDTO attendee = new UserDTO();
		attendee.setId("1");
		AttendanceDTO attendanceToCreate = new AttendanceDTO();
		attendanceToCreate.setId("1");
		attendanceToCreate.setEvent(event);
		attendanceToCreate.setUser(attendee);
		attendanceToCreate.setOrganizer(false);
		attendanceToCreate.setNumPartecipanti(22);
		
		WebTarget targetJSON = client.target(getBaseURI()).path("events").path(attendee.getId()).path("attendances").path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).post(Entity.entity(attendanceToCreate, MediaType.APPLICATION_JSON));
		assertEquals(200, responseJSON.getStatus());
		AttendanceDTO entityFromJSON = responseJSON.readEntity(AttendanceDTO.class);
		assertNotNull(entityFromJSON);
		assertNotEquals(entityFromJSON.getId(), "-1");
		
		WebTarget targetXML = client.target(getBaseURI()).path("events").path(attendee.getId()).path("attendances").path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).post(Entity.entity(attendanceToCreate, MediaType.APPLICATION_XML));
		assertEquals(200, responseXML.getStatus());
		AttendanceDTO entityFromXML = responseXML.readEntity(AttendanceDTO.class);
		assertNotNull(entityFromXML);
		assertNotEquals(entityFromXML.getId(), "-1");
		
	}	
	
	@Test
	public void deleteEventShouldReturnConfirm(){
		Client client = ClientBuilder.newBuilder().build();
		client.register(EventDTOMessageBodyRW.class);
		client.register(WriteResultDTOMessageBodyRW.class);
		
		String eventToDelete = "1";
		
		WebTarget targetJSON = client.target(getBaseURI()).path("events").path(eventToDelete).path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).delete();
		checkEntityWriteResultJSON(responseJSON);
		
		WebTarget targetXML = client.target(getBaseURI()).path("events").path(eventToDelete).path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).delete();
		checkEntityWriteResultXML(responseXML);	
	}	
	
	@Test
	public void createEventShouldReturnConfirm(){
		Client client = ClientBuilder.newBuilder().build();
		client.register(EventDTOMessageBodyRW.class);
		client.register(WriteResultDTOMessageBodyRW.class);
		
		UserDTO organizer = new UserDTO();
		organizer.setId("1");
		organizer.setNome("Nome organizer");
		organizer.setCognome("Cognome organizer");
		
		EventDTO dto = new EventDTO();
		dto.setNome("nuovo evento");
		dto.setUserOrganizer(organizer);
		
		WebTarget targetJSON = client.target(getBaseURI()).path("events").path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).post(Entity.entity(dto, MediaType.APPLICATION_JSON));
		checkEntityWriteResultJSON(responseJSON);
		
		WebTarget targetXML = client.target(getBaseURI()).path("events").path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).post(Entity.entity(dto, MediaType.APPLICATION_XML));
		checkEntityWriteResultXML(responseXML);	
	}	
	
	@Test
	public void getAttendacesByEventShouldReturnAtLeastOneAttendance(){
		Client client = ClientBuilder.newBuilder().build();
		client.register(AttendanceDTOMessageBodyRW.class);

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
		Client client = ClientBuilder.newBuilder().build();
		client.register(EventDTOMessageBodyRW.class);

		String requestedEventId = "1";
		
		WebTarget targetJSON = client.target(getBaseURI()).path("events").path(requestedEventId).path("json");
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).get();
		assertEquals(200, responseJSON.getStatus());
		final EventDTO entityFromJSON = responseJSON.readEntity(EventDTO.class);
		assertNotNull(entityFromJSON);
		assertEquals(entityFromJSON.getEventId(), requestedEventId);
		
		WebTarget targetXML = client.target(getBaseURI()).path("events").path(requestedEventId).path("xml");
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).get();
		assertEquals(200, responseXML.getStatus());
		final EventDTO entityFromXML = responseXML.readEntity(EventDTO.class);
		assertNotNull(entityFromXML);
		assertEquals(entityFromXML.getEventId(), requestedEventId);
	}
	
	@Test
	public void searchEventsShouldReturnAtLeastOneEvent(){
		Client client = ClientBuilder.newBuilder().build();
		client.register(EventDTOMessageBodyRW.class);
		
		String posizione = "posizione1";
		String categoria = "categoria1";
		int partecipanti = 3;
		
		WebTarget targetJSON = client.target(getBaseURI()).path("events").path("json").
				queryParam("posizione", posizione).
				queryParam("categoria", categoria).
				queryParam("partecipanti", partecipanti);
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).get();
		assertEquals(200, responseJSON.getStatus());
		final List<EventDTO> entityFromJSON = responseJSON.readEntity(new GenericType<List<EventDTO>>(){});
		assertNotNull(entityFromJSON);
		assertTrue(entityFromJSON.size()>0);
		
		WebTarget targetXML = client.target(getBaseURI()).path("events").path("xml").
				queryParam("posizione", posizione).
				queryParam("categoria", categoria).
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
		eventServiceTest.addFeedbackShouldReturnConfirm();
	}
	
}
