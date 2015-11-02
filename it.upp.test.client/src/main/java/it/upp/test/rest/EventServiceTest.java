package it.upp.test.rest;

import java.net.URI;
import it.fff.business.common.dto.*;
import it.fff.business.service.provider.EventDTOMessageBodyRW;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

public class EventServiceTest {
	
	public static void main(String[] args) {
		Client client = ClientBuilder.newBuilder().build();
		client.register(EventDTOMessageBodyRW.class);
		
		
		WebTarget targetGetEventJSON = client.target(getBaseURI());
		Response responseGetEventJSON = targetGetEventJSON.path("events").path("1").path("json").
				request(MediaType.APPLICATION_JSON).
				get();
		final EventDTO responseGetEventJSONEntity = responseGetEventJSON.readEntity(EventDTO.class);
		System.out.println("*************");
		System.out.println(responseGetEventJSON.getStatus());
		System.out.println(responseGetEventJSON.getMediaType());
		System.out.println(responseGetEventJSONEntity);
		
		WebTarget targetGetEventXML = client.target(getBaseURI());
		Response responseGetEventXML = targetGetEventXML.path("events").path("1").path("xml").
				request(MediaType.APPLICATION_XML).
				get();
		final EventDTO responseGetEventXMLEntity = responseGetEventXML.readEntity(EventDTO.class);
		System.out.println("*************");
		System.out.println(responseGetEventXML.getStatus());
		System.out.println(responseGetEventXML.getMediaType());
		System.out.println(responseGetEventXMLEntity);		
	}

	  private static URI getBaseURI() {
		    return UriBuilder.fromUri("http://localhost:8080/it.fff.business.service.webapp/restapi").build();
		  }
}
