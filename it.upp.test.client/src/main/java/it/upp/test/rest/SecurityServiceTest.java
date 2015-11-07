package it.upp.test.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import it.fff.business.common.dto.PlaceDTO;
import it.fff.business.service.provider.PlaceDTOMessageBodyRW;
import it.fff.business.service.provider.WriteResultDTOMessageBodyRW;

public class SecurityServiceTest extends ServiceTest{
	
	@Test
	public void loginShouldReturnConfirm(){
		Client client = ClientBuilder.newBuilder().build();
		client.register(PlaceDTOMessageBodyRW.class);
		client.register(WriteResultDTOMessageBodyRW.class);
		
		String username = "lucap84@gmail.com";
		String password = "mypassword";
		
		WebTarget targetJSON = client.target(getBaseURI()).path("security").path("login").path("json").
				queryParam("username", username).
				queryParam("password", password);
		Response responseJSON = targetJSON.request(MediaType.APPLICATION_JSON).post(null);
		checkEntityWriteResultJSON(responseJSON);
		
		WebTarget targetXML = client.target(getBaseURI()).path("security").path("login").path("xml").
				queryParam("username", username).
				queryParam("password", password);
		Response responseXML = targetXML.request(MediaType.APPLICATION_XML).post(null);
		checkEntityWriteResultXML(responseXML);	
	}	

}
