package it.fff.client.stub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.fff.client.wsrest.WebServiceRestTest;
import it.fff.clientserver.common.dto.PlaceDTO;

public class PlaceServiceStub extends StubService{

	public List<PlaceDTO> getPlacesByDescription(String description, String mediaType){
		Client client = super.getClientInstance();
		
		List<PlaceDTO> entityFromJSON = null;
		
		String restPath="places/"+mediaType.toLowerCase().substring("application/".length());
		{//Test JSON
			Builder requestBuilderJSON = client.target(getBaseURI()).path(restPath).
					queryParam("description", description).
					request(mediaType);
			Response responseJSON = requestBuilderJSON.get();
			entityFromJSON = responseJSON.readEntity(new GenericType<List<PlaceDTO>>(){});
		}
		
		return entityFromJSON;
	}	
}
