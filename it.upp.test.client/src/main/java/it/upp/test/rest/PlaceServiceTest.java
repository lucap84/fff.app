package it.upp.test.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import com.sun.research.ws.wadl.HTTPMethods;

import it.fff.clientserver.common.dto.*;
import it.upp.test.secure.ClientSecureConfiguration;

public class PlaceServiceTest extends WebServiceRestTest{
	
	public PlaceServiceTest(){
	}	
	
	@Test
	public void getPlacesByDescriptionShouldReturnAtLeastOnePlace(){
		Client client = WebServiceRestTest.getClientInstance();
		
		String description = "chiringuito";
		
		String restPath="places";
		{//Test JSON
			String restPathJSON=restPath+"/json";
			Builder requestBuilderJSON = client.target(getBaseURI()).path(restPathJSON).
					queryParam("description", description).request(MediaType.APPLICATION_JSON);
			Response responseJSON = requestBuilderJSON.get();
			assertEquals(200, responseJSON.getStatus());
			final List<PlaceDTO> entityFromJSON = responseJSON.readEntity(new GenericType<List<PlaceDTO>>(){});
			assertNotNull(entityFromJSON);
			assertTrue(entityFromJSON.size()>0);
		}
		{//Test XML
			String restPathXML=restPath+"/xml";
			Builder requestBuilderXML = client.target(getBaseURI()).path(restPathXML).
					queryParam("description", description).request(MediaType.APPLICATION_XML);
			Response responseXML = requestBuilderXML.get();
			assertEquals(200, responseXML.getStatus());
			List<PlaceDTO> entityFromXML = responseXML.readEntity(new GenericType<List<PlaceDTO>>(){});
			assertNotNull(entityFromXML);
			assertTrue(entityFromXML.size()>0);
		}
	}	

	public static void main(String[] args) {
//		new PlaceServiceTest("1","99").getPlacesByDescriptionShouldReturnAtLeastOnePlace();
	}
}
