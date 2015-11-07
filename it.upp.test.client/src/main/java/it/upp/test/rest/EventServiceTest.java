package it.upp.test.rest;

import it.fff.business.common.dto.*;
import it.fff.business.service.provider.EventDTOMessageBodyRW;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class EventServiceTest extends ServiceTest{
	
	@Test
	public void getEventShouldReturnOneEvent(){
		Client client = ClientBuilder.newBuilder().build();
		client.register(EventDTOMessageBodyRW.class);

		String requestedEventId = "1";
		
		WebTarget targetJSON = client.target(getBaseURI()).path("events").path(requestedEventId).path("json");
		Response responseGetEventJSON = targetJSON.request(MediaType.APPLICATION_JSON).get();
		assertEquals(200, responseGetEventJSON.getStatus());
		final EventDTO entityFromJSON = responseGetEventJSON.readEntity(EventDTO.class);
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
		eventServiceTest.searchEventsShouldReturnAtLeastOneEvent();
	}
	
}
