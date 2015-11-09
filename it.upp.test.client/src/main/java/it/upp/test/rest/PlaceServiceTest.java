package it.upp.test.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import it.fff.clientserver.common.dto.*;

public class PlaceServiceTest extends ServiceTest{
	
	
	@Test
	public void getPlacesByDescriptionShouldReturnAtLeastOnePlace(){
		Client client = ServiceTest.getClientInstance();
		
		String description = "chiringuito";
		
		WebTarget targetJSON = client.target(getBaseURI()).path("places").path("json").
				queryParam("description", description);
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).get();
		assertEquals(200, responseJSON.getStatus());
		final List<PlaceDTO> entityFromJSON = responseJSON.readEntity(new GenericType<List<PlaceDTO>>(){});
		assertNotNull(entityFromJSON);
		assertTrue(entityFromJSON.size()>0);
		
		WebTarget targetXML = client.target(getBaseURI()).path("places").path("xml").
				queryParam("description", description);
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).get();
		assertEquals(200, responseXML.getStatus());
		List<PlaceDTO> entityFromXML = responseXML.readEntity(new GenericType<List<PlaceDTO>>(){});
		assertNotNull(entityFromXML);
		assertTrue(entityFromXML.size()>0);
	}	

}
