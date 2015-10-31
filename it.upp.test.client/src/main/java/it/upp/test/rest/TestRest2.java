package it.upp.test.rest;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.fff.business.common.dto.IdentifierDTO;
import it.fff.business.common.util.UtilDTO;

public class TestRest2 {
	
	public static void main(String[] args) {
		final Client client = ClientBuilder.newClient();
		
		WebTarget webTarget = client.target(getBaseURI());
		
		IdentifierDTO identifierDTO = new IdentifierDTO();
		identifierDTO.setId(1);
		
		String dto2EncodedJSONString = UtilDTO.dto2EncodedJSONString(identifierDTO);
		Response response = webTarget.path("events").queryParam("json", dto2EncodedJSONString)
                .request().accept(MediaType.APPLICATION_JSON).get();
		System.out.println(response.getStatus());
		String myBeanXml = response.readEntity(String.class);
		System.out.println(myBeanXml);
	
	}

	  private static URI getBaseURI() {
		    return UriBuilder.fromUri("http://localhost:8080/it.fff.business.service.webapp/restapi").build();
		  }
}
