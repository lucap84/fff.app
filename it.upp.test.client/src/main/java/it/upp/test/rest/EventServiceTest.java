package it.upp.test.rest;

import java.net.URI;
import it.fff.business.common.dto.*;
import it.fff.business.service.provider.CreateUserDTOMessageBodyRW;
import it.fff.business.service.provider.EventDTOMessageBodyRW;
import it.fff.business.service.provider.WriteResultDTOMessageBodyRW;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.junit.Test;
import static org.junit.Assert.*;

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
	
}
